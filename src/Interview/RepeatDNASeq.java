package Interview;

/**
 * Created by yongyangyu on 2/6/15.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * find all the 10-letter-long sequences that occur more than once in a DNA molecule
 */
public class RepeatDNASeq {
    public static List<String> findReapeatedDnaSequences(String s) {
        List<String> res = new LinkedList<String>();
        Map<Character, Integer> dna = new HashMap<Character, Integer>();
        dna.put('A', 0x0);
        dna.put('C', 0x1);
        dna.put('G', 0x2);
        dna.put('T', 0x3);
        if (s.length() < 10) {
            return res;
        }
        Map<Integer, Integer> dic = new HashMap<Integer, Integer>();
        String sub = s.substring(0, 10);
        int curr = initialSig(sub, dna);
        dic.put(curr, 1);
        for (int i = 10; i < s.length(); i ++) {
            char last = s.charAt(i);
            curr = computeSig(curr, last, dna);
            if (dic.containsKey(curr)) {
                if (dic.get(curr) < 2) {
                    dic.put(curr, dic.get(curr) + 1);
                    res.add(s.substring(i - 9, i + 1));
                }
            }
            else {
                dic.put(curr, 1);
            }
        }
        return res;
    }

    private static int initialSig(String sub, Map<Character, Integer> dna) {
        int sig = 0x0;
        for (int i = 0; i < sub.length(); i ++) {
            sig = (sig << 2) | dna.get(sub.charAt(i));
        }
        return sig;
    }


    private static int computeSig(int curr, char last, Map<Character, Integer> dna) {

        int sig = curr;
        sig = sig & 0x3FFFF;
        sig = (sig << 2) | dna.get(last);
        return sig;
    }

    public static void main (String[] args) {
        String s = "AAAAAAAAAAAA";
        List<String> res = findReapeatedDnaSequences(s);
        for (String x: res) {
            System.out.println(x);
        }
    }
}
