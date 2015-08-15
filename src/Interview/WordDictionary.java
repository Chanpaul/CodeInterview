package Interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Created by yongyangyu on 5/24/15.
 */
public class WordDictionary {
    private Map<Character, HashMap> root;

    public WordDictionary() {
        root = new HashMap<Character, HashMap>();
    }

    public WordDictionary(Map<Character, HashMap> node) {
        root = node;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        Map<Character, HashMap> node = root;
        word = word + "$";
        for (int i = 0; i < word.length(); i ++) {
            Character currLetter = word.charAt(i);
            if (!node.containsKey(currLetter)) {
                node.put(currLetter, new HashMap<Character, HashMap>());
            }
            node = node.get(currLetter);
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        Map<Character, HashMap> node = root;
        word = word + "$";
        for (int i = 0; i < word.length(); i ++) {
            char currLetter = word.charAt(i);
            if (currLetter != '.') {
                if (!node.containsKey(currLetter)) {
                    return false;
                }
                node = node.get(currLetter);
            }
            else { // current letter is '.'
                String subWord = word.substring(i+1, word.length()-1);
                Set<Character> keys = node.keySet();
                for (Character ch : keys) {
                    WordDictionary wd = new WordDictionary(node.get(ch));
                    if (wd.search(subWord)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
        System.out.println(wordDictionary.search("ba.."));
    }
}
