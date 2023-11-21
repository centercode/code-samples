package io.github.centercode.algorithm.string;

import java.util.ArrayDeque;

/**
 * 150. 逆波兰表达式求值：
 * 有效的算符为 '+'、'-'、'*' 和 '/'
 */
public class EvalRPN {

    public int solution1(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    Integer val2 = stack.pop();
                    Integer val1 = stack.pop();
                    stack.push(val1 + val2);
                    break;
                case "-":
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 - val2);
                    break;
                case "*":
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 * val2);
                    break;
                case "/":
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 / val2);
                    break;
                default:
                    Integer val = Integer.valueOf(token);
                    stack.push(val);
            }
        }
        return stack.pop();
    }

    int pos = 0;

    public int solution2(String[] tokens) {
        this.pos = tokens.length - 1;
        return eval(tokens);
    }

    public int eval(String[] tokens) {
        String token = tokens[pos];
        if (token.equals("*")) {
            pos--;
            return eval(tokens) * eval(tokens);
        }
        if (token.equals("+")) {
            pos--;
            return eval(tokens) + eval(tokens);
        }
        if (token.equals("/")) {
            pos--;
            int op = eval(tokens);
            return eval(tokens) / op;
        }
        if (token.equals("-")) {
            pos--;
            int op = eval(tokens);
            return eval(tokens) - op;
        }
        pos--;
        return Integer.parseInt(token);
    }
}
