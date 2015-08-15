package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 5/12/15.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        Map<Character, HashMap> node = root.getNode();
        word = word + "$";
        for (int i = 0; i < word.length(); i ++) {
            Character currLetter = word.charAt(i);
            if (!node.containsKey(currLetter)) {
                node.put(currLetter, new HashMap<Character, HashMap>());
            }
            node = node.get(currLetter);
        }
    }

    // returns true if the word is in the trie
    public boolean search(String word) {
        Map<Character, HashMap> node = root.getNode();
        word = word + "$";
        for (int i = 0; i < word.length(); i ++) {
            Character currLetter = word.charAt(i);
            if (!node.containsKey(currLetter)) {
                return false;
            }
            node = node.get(currLetter);
        }
        return true;
    }

    // Returns true if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        Map<Character, HashMap> node = root.getNode();
        for (int i = 0; i < prefix.length(); i ++) {
            Character currLetter = prefix.charAt(i);
            if (node.containsKey(currLetter)) {
                node = node.get(currLetter);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("about");
        trie.insert("aban");
        trie.insert("ban");
        trie.insert("banana");
        System.out.println(trie.search("ba"));
        System.out.println(trie.startsWith("ab"));
    }
}
