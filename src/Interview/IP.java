package Interview;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by yongyangyu on 12/3/14.
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 *
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class IP {
    private static boolean check(String s, int[] pos) {
        StringBuilder sb = new StringBuilder(s);
        String s1 = sb.substring(0, pos[0]+1);
        String s2 = sb.substring(pos[0]+1, pos[1]+1);
        String s3 = sb.substring(pos[1]+1, pos[2]+1);
        String s4 = sb.substring(pos[2]+1, s.length());
        try {
            if (s1.length() < 1 || Integer.parseInt(s1) > 255 || (s1.length() > 1 && s1.charAt(0) == '0')) {
                return false;
            }
            if (s2.length() < 1 || Integer.parseInt(s2) > 255 || (s2.length() > 1 && s2.charAt(0) == '0')) {
                return false;
            }
            if (s3.length() < 1 || Integer.parseInt(s3) > 255 || (s3.length() > 1 && s3.charAt(0) == '0')) {
                return false;
            }
            if (s4.length() < 1 || Integer.parseInt(s4) > 255 || (s4.length() > 1 && s4.charAt(0) == '0')) {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }


    public static List<String> getIP(String s) {
        List<String> res = new LinkedList<String>();
        if (s.length() > 12) {
            return res;
        }
        int[] pos = new int[3];
        for (int i = 0; i < s.length(); i ++) {
            for (int j = i + 1; j < s.length(); j ++) {
                for (int k = j + 1; k < s.length(); k ++) {
                    pos[0] = i;
                    pos[1] = j;
                    pos[2] = k;
                    if (check(s, pos)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(s.substring(0, pos[0] + 1));
                        sb.append('.');
                        sb.append(s.substring(pos[0]+1, pos[1] + 1));
                        sb.append('.');
                        sb.append(s.substring(pos[1]+1, pos[2] + 1));
                        sb.append('.');
                        sb.append(s.substring(pos[2]+1, s.length()));
                        res.add(sb.toString());
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "010010";//"19216811";
        List<String> arr = getIP(s);
        System.out.println("There are " + arr.size() + " combinations:");
        for (String x : arr) {
            System.out.println(x);
        }
    }
}
