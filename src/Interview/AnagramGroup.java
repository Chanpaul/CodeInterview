package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/29/15.
 * Given a list of strings, ["cab", "cz", "abc", "bca", "zc"],
 * output like [["abc", "bca", "cab"], ["zc", "cz"]]
 */
public class AnagramGroup {
    public List<List<String>> anagrams(String[] input) {
        List<List<String>> output = new ArrayList<>();
        Map<Map<Character, Integer>, List<String>> map = new HashMap<>();
        for (String str: input) {
            Map<Character, Integer> curr = new HashMap<>();
            for (int i = 0; i < str.length(); i ++) {
                char ch = str.charAt(i);
                if (!curr.containsKey(ch)) {
                    curr.put(ch, 1);
                }
                else {
                    int count = curr.get(ch);
                    curr.put(ch, count+1);
                }
            }
            if (map.containsKey(curr)) {
                map.get(curr).add(str);
            }
            else {
                List<String> tmp = new ArrayList<>();
                tmp.add(str);
                map.put(curr, tmp);
            }
        }
        for (List<String> elem: map.values()) {
            output.add(elem);
        }
        return output;
    }

    public static void main(String[] args) {
        String[] strs = {"cab", "cz", "abc", "bca", "zc"};
        List<List<String>> res = new AnagramGroup().anagrams(strs);
        for (List<String> x: res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
