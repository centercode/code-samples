package io.github.centercode.algorithm.number;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolveSudokuTest {

    @Test
    public void solveSudoku() {
        char[][] board = {
            new char[] {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            new char[] {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            new char[] {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            new char[] {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            new char[] {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            new char[] {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            new char[] {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            new char[] {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            new char[] {'.', '.', '.', '.', '8', '.', '.', '7', '9'}

        };
        char[][] expect = {
            new char[] {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
            new char[] {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
            new char[] {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
            new char[] {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
            new char[] {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
            new char[] {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
            new char[] {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
            new char[] {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
            new char[] {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
        };
        SolveSudoku solveSudoku = new SolveSudoku();
        solveSudoku.solution1(board);
        assertArrayEquals(expect, board);
    }
}