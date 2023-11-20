package io.github.centercode.algorithm.number;

import org.junit.Test;

import java.util.List;

public class NQueensTest {

    @Test
    public void solution1() {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solution1(4);
        for (int i = 0; i < res.size(); i++) {
            List<String> solution = res.get(i);
            System.out.println("solution " + i + ":");
            for (String s : solution) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void solution2() {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solution2(4);
        for (int i = 0; i < res.size(); i++) {
            List<String> solution = res.get(i);
            System.out.println("solution " + i + ":");
            for (String s : solution) {
                System.out.println(s);
            }
        }
    }
}