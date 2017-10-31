package zhenyu.sha.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class WordDictionary {
    class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean isTermination;
    }

    final private TrieNode myTrie;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        myTrie = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        addWordInternal(myTrie, word);

    }

    private void addWordInternal(TrieNode node, String word) {
        if (null == word || word.length() == 0) {
            node.isTermination = true;
        } else {
            TrieNode child = node.children.get(word.charAt(0));
            if (null == child) {
                child = new TrieNode();
                node.children.put(word.charAt(0), child);
            }
            addWordInternal(child, word.substring(1));
        }

    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchInternal(myTrie, word);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean searchInternal(TrieNode node, String word) {
        if (null == word || 0 == word.length()) {
            return node.isTermination;
        }
        String childString = word.substring(1);
        if ('.' == word.charAt(0)) {
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                if (searchInternal(entry.getValue(), childString)) {
                    return true;
                }
            }
            return false;
        } else {
            TrieNode child = node.children.get(word.charAt(0));
            return null != child && searchInternal(child, word.substring(1));
        }
    }

    public static void main(String[]args){
        WordDictionary dic = new WordDictionary();
        dic.addWord("bad");
        dic.addWord("dad");
        dic.addWord("mad");

        System.out.println(dic.search("bad"));
    }
}
