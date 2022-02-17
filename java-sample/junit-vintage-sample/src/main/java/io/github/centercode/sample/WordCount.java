package io.github.centercode.sample;

import java.util.StringTokenizer;

public class WordCount {

    int count(String content) {
        int count = 0;
        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            tokenizer.nextToken();
            count++;
        }
        return count;
    }
}
