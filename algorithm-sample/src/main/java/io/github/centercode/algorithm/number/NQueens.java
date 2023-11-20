package io.github.centercode.algorithm.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public List<List<String>> solution1(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> res = new ArrayList<>();
        // whether column is used
        Set<Integer> usedColumns = new HashSet<>();
        // whether the diagonal from top left to bottom right is used
        Set<Integer> usedDiagonals1 = new HashSet<>();
        // whether the diagonal from bottom left to top right is used
        Set<Integer> usedDiagonals2 = new HashSet<>();
        dfs(queens, 0, usedColumns, usedDiagonals1, usedDiagonals2, res);
        return res;
    }

    /**
     * @param queens marked the used position have been taken
     * @param x 要尝试皇后的行号
     * @param usedColumns 已经落子的列坐标
     * @param usedDiagonals1 已经落子的从左上到右下的方向
     * @param usedDiagonals2 已经落子的从左下到右上的方向
     * @param res
     * @return
     */
    private boolean dfs(
        int[] queens,
        int x,
        Set<Integer> usedColumns,
        Set<Integer> usedDiagonals1,
        Set<Integer> usedDiagonals2,
        List<List<String>> res
    ) {
        int n = queens.length;
        if (x == n) {
            List<String> solution = generateBoard(n, queens);
            res.add(solution);
        }
        for (int y = 0; y < n; y++) {
            // 从左上到右下的方向上的点横纵坐标的差是相同的
            int diagonal1 = x - y;
            // 从左下到右上的方向上的点横纵坐标的和是相同的
            int diagonal2 = x + y;
            if (usedColumns.contains(y) || usedDiagonals1.contains(diagonal1) || usedDiagonals2.contains(diagonal2)) {
                continue;
            }
            // 尝试落子
            queens[x] = y;
            usedColumns.add(y);
            usedDiagonals1.add(diagonal1);
            usedDiagonals2.add(diagonal2);
            // 递归尝试下一行上皇后的位置
            dfs(queens, x + 1, usedColumns, usedDiagonals1, usedDiagonals2, res);
            // 撤回落子
            queens[x] = -1;
            usedColumns.remove(y);
            usedDiagonals1.remove(diagonal1);
            usedDiagonals2.remove(diagonal2);
        }
        return false;
    }

    /**
     * generate solution board
     */
    private static List<String> generateBoard(int n, int[] queens) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            String line = sb.toString();
            solution.add(line);
        }
        return solution;
    }


    public List<List<String>> solution2(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] line : board) {
            Arrays.fill(line, '.');
        }
        dfs(board, 0, result);
        return result;
    }

    public void dfs(char[][] board, int row, List<List<String>> result) {
        int n = board.length;
        if (row == n) {
            List<String> path = new ArrayList<>();
            for (char[] line : board) {
                path.add(new String(line));
            }
            result.add(path);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(board, row, i)) {
                board[row][i] = 'Q';
                dfs(board, row + 1, result);
                board[row][i] = '.';
            }
        }
    }

    public boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        for (int i = row; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

}
