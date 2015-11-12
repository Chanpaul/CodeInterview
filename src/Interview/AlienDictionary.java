package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 11/11/15.
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you. You receive a list of words
 * from the dictionary, wherewords are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * For example,
 * Given the following words in dictionary,
 *
 *  [
 *    "wrt",
 *    "wrf",
 *    "er",
 *    "ett",
 *    "rftt"
 *  ]
 * The correct order is: "wertf".
 *
 * Note:
 *
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    private Map<Character, Set<Character>> graph;
    private Set<Character> charSet;

    public AlienDictionary(String[] words) {
        createGraph(words);
    }

    private Map<Character, Set<Character>> createGraph(String[] words) {
        makeCharSet(words);
        graph = new HashMap<>();
        for (char c: charSet) {
            graph.put(c, new HashSet<>());
        }
        for (int i = 0; i < words.length-1; i ++) {
            for (int j = i+1; j < words.length; j ++) {
                for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k ++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        graph.get(words[j].charAt(k)).add(words[i].charAt(k)); // words[j](k) depends on words[i](k)
                        charSet.remove(words[i].charAt(k));
                        charSet.remove(words[j].charAt(k));
                        break;
                    }
                }
            }
        }
        return graph;
    }

    private void makeCharSet(String[] words) {
        charSet = new HashSet<>();
        for (String s: words) {
            for (int i = 0; i < s.length(); i ++) {
                charSet.add(s.charAt(i));
            }
        }
    }

    private Map<Character, Integer> indegree() {
        Map<Character, Integer> degree = new HashMap<>();
        for (char c: graph.keySet()) {
            if (!degree.containsKey(c)) degree.put(c, graph.get(c).size());
        }
        return degree;
    }

    public String alienOrder() {
        Map<Character, Integer> degree = indegree();
        StringBuilder sb = new StringBuilder();
        List<Character> queue = new ArrayList<>();
        for (char c: degree.keySet()) {
            if (degree.get(c) == 0)
                queue.add(c);
        }
        while (!queue.isEmpty()) {
            char curr = queue.remove(0);
            sb.append(curr);
            for (char c: graph.keySet()) {
                if (graph.get(c).contains(curr)) {
                    graph.get(c).remove(curr);
                    if (graph.get(c).isEmpty()) queue.add(c);
                }
            }
        }
        if (sb.length() < degree.keySet().size()) return "";
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        AlienDictionary ad = new AlienDictionary(words);
        System.out.println(ad.alienOrder());
    }
}
