package io;

import java.util.Scanner;

public class ScannerSample {
    public static void main(String[] args) {
        continueInputLines();
    }

    /**
     * 回车触发，token分隔
     */
    private static void continueInputTokens() {
        System.out.println("Please input:");
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                System.out.println("TOKEN: " + scanner.next());
            }
        }
    }

    /**
     * 回车触发，换行符分隔
     */
    private static void continueInputLines() {
        System.out.println("Please input lines:");
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                System.out.println("LINE: " + scanner.nextLine());
            }
        }

    }
}
