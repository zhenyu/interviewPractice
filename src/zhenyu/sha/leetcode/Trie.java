package zhenyu.sha.leetcode;

import java.util.Map;
import java.util.TreeMap;
//TODO not recurssive version with Node
public class Trie {
    final Map<Character, Trie> children;
    boolean isTermination;
    /** Initialize your data structure here. */
    public Trie() {
        children = new TreeMap<>();
        isTermination = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(null == word || 0 == word.length()) {
            isTermination = true;
            return;
        }

        Trie child = children.get(word.charAt(0));
        if(null == child) {
            child = new Trie();
            children.put(word.charAt(0), child);
        }
        child.insert(word.substring(1));
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie result = searchPrefix(word);
        return null!=result && true == result.isTermination;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return null!=searchPrefix(prefix);
    }

    private Trie searchPrefix(String prefix) {
        if(null == prefix || 0 == prefix.length())
            return this;
        Trie child = children.get(prefix.charAt(0));
        if(null == child)
            return null;
        return child.searchPrefix(prefix.substring(1));

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */