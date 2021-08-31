package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void insert() {
        Trie trie = new Trie();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));
    }
}