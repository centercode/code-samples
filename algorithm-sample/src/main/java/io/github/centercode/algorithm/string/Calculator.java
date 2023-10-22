package io.github.centercode.algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Calculator {

    /**
     * 224. 基本计算器：s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
     * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
     * 解法一：双栈法
     */
    public int case1Solution1(String s) {
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);
        s = s.replaceAll(" ", "");
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    Character op = ops.peek();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else if (Character.isDigit(ch)) {
                int num = 0;
                int j = i;
                // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                for (; j < s.length() && Character.isDigit(s.charAt(j)); j++) {
                    num = num * 10 + s.charAt(j) - '0';
                }
                nums.push(num);
                i = j - 1;
            } else {
                // 处理正负号
                if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-')) {
                    nums.push(0);
                }
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                while (!ops.isEmpty() && ops.peek() != '(') {
                    calc(nums, ops);
                }
                ops.push(ch);
            }
        }
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peek();
    }

    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (ops.isEmpty() || nums.isEmpty() || nums.size() < 2) {
            return;
        }
        int b = nums.pop();
        int a = nums.pop();
        char op = ops.pop();
        nums.push(op == '+' ? a + b : a - b);
    }

    /**
     * 227. 基本计算器 II：s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * 解法一：栈
     */
    public int case2Solution1(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        throw new IllegalStateException();
                }
                preSign = ch;
                num = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 772. 基本计算器 III：
     * 实现一个基本的计算器来计算简单的表达式字符串。
     * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。
     * 整数除法需要向下截断 。
     * 参考：https://www.cnblogs.com/grandyang/p/8873471.html
     */
    public int case3Solution1(String s) {
        int n = s.length();
        // 最终结果
        int res = 0;
        // 前一个符号之前的数字，仅当前一个符号为乘除号时不为零
        int preNum = 0;
        // 前一个符号之后的数字
        int num = 0;
        // 前一个符号
        char preOp = '+';
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '(') {
                // 递归计算括号里面表达式的值
                int j = i, cnt = 0;
                for (; j < n; j++) {
                    if (s.charAt(j) == '(') {
                        ++cnt;
                    } else if (s.charAt(j) == ')') {
                        --cnt;
                    }
                    if (cnt == 0) {
                        break;
                    }
                }
                num = case3Solution1(s.substring(i + 1, j));
                // 继续括号子表达式后面的计算
                i = j;
            }
            // 当前字符是操作符或者结尾，可以计算前一个操作符和其后的数
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == n - 1) {
                // 如果前一个操作符是乘除号则preNum是被除数或被乘数，num是除数或乘数；
                // 如果前一个操作符是加减号则preNum在第170行已经被置零
                switch (preOp) {
                    case '+':
                        preNum += num;
                        break;
                    case '-':
                        preNum -= num;
                        break;
                    case '*':
                        preNum *= num;
                        break;
                    case '/':
                        preNum /= num;
                        break;
                }
                // 当前符号是加减号，则当前符号的优先级肯定不大于前一个符号的优先级，前一个符号的中间计算结果可以计入最终结果
                if (ch == '+' || ch == '-' || i == n - 1) {
                    res += preNum;
                    preNum = 0;
                }
                preOp = ch;
                num = 0;
            }
        }
        return res;
    }

    /**
     * 单栈法，参考：https://blog.csdn.net/qq_15711195/article/details/126114213
     */
    public int case3Solution2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char sign = '+';
        int num = 0;
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (!Character.isDigit(c) && c != ' ' || i == len - 1) {
                if (c == '(') {
                    int j = i, count = 0;
                    for (; i < len; i++) {
                        if (s.charAt(i) == '(') {
                            count++;
                        }
                        if (s.charAt(i) == ')') {
                            count--;
                        }
                        if (count == 0) {
                            break;
                        }
                    }
                    num = case3Solution2(s.substring(j + 1, i));
                }
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                sign = c; //before next number
            }
        }
        for (int val : stack) {
            res += val;
        }
        return res;
    }

    /**
     * 双栈法，参考：https://blog.csdn.net/qq_15711195/article/details/126114213
     */
    public int case3Solution3(String s) {
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('(', 1);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put('*', 3);
        priority.put('/', 3);
        Deque<Long> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (ch == '(') {
                ops.push('(');
            } else if (ch == ')') {
                while (ops.peek() != '(') {
                    case3Solution3Calc(nums, ops);
                }
                ops.pop();
            } else if (Character.isDigit(ch)) {
                int j = i;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    j++;
                }
                long num = Long.parseLong(s.substring(i, j));
                nums.push(num);
                i = j - 1;
            } else {
                while (!ops.isEmpty() && priority.get(ops.peek()) >= priority.get(ch)) {
                    case3Solution3Calc(nums, ops);
                }
                ops.push(ch);
            }
        }
        while (nums.size() > 1) {
            case3Solution3Calc(nums, ops);
        }
        return nums.peek().intValue();
    }

    private void case3Solution3Calc(Deque<Long> nums, Deque<Character> ops) {
        long n2 = nums.pop(), n1 = nums.pop();
        switch (ops.pop()) {
            case '+':
                nums.push(n1 + n2);
                break;
            case '-':
                nums.push(n1 - n2);
                break;
            case '*':
                nums.push(n1 * n2);
                break;
            case '/':
                nums.push(n1 / n2);
                break;
        }
    }

}
