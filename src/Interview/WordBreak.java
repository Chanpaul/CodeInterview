package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 1/21/15.
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where
 * each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreak {
    public static List<String> wordBreak(String s, Set<String> dict) {
        List<String> rval = null;
        Map<Integer, LinkedList<LinkedList<String>>> map = new HashMap<Integer, LinkedList<LinkedList<String>>>();
        if (s.length() == 0 && dict.size() == 0) {
            rval = new LinkedList<String>();
            rval.add("");
        }
        if (dict.contains(String.valueOf(s.charAt(0)))) {
            LinkedList<LinkedList<String>> tmp = new LinkedList<LinkedList<String>>();
            tmp.add(new LinkedList<String>());
            tmp.get(0).add(String.valueOf(s.charAt(0)));
            map.put(0, tmp);
        }
        for (int i = 1; i < s.length(); i ++) {
            if (dict.contains(s.substring(0, i + 1))) {
                LinkedList<LinkedList<String>> tmp = new LinkedList<LinkedList<String>>();
                tmp.add(new LinkedList<String>());
                tmp.get(0).add(s.substring(0, i + 1));
                map.put(i, tmp);
            }
            for (int j = i-1; j >= 0; j --) {
                if (dict.contains(s.substring(j+1, i+1)) && map.containsKey(j)) {
                    LinkedList<LinkedList<String>> tmp = new LinkedList<LinkedList<String>>(map.get(j));
                    for (int k = 0; k < tmp.size(); k ++) {
                        tmp.get(k).add(s.substring(j+1, i + 1));
                    }
                    LinkedList<LinkedList<String>> curr = null;
                    if (map.containsKey(i)) {
                        curr = map.get(i);
                    }
                    if (curr == null) {
                        curr = new LinkedList<LinkedList<String>>();
                    }
                    for (LinkedList<String> x : tmp) {
                        curr.add(x);
                    }
                    map.put(i, curr);
                }
            }
        }
        if (map.containsKey(s.length() - 1)) {
            rval = new LinkedList<String>();
            for (LinkedList<String> x : map.get(s.length() - 1)) {
                StringBuilder sb = new StringBuilder();
                sb.append(x.get(0));
                for (int n = 1; n < x.size(); n ++) {
                    sb.append(' ');
                    sb.append(x.get(n));
                }
                rval.add(sb.toString());
            }
        }
        return rval;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        Set<String> dict = new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        List<String> res = wordBreak(s, dict);
        for (String x : res) {
            System.out.println(x);
        }
    }
}
