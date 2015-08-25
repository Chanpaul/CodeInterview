package Interview;

/**
 * Created by yongyangyu on 8/24/15.
 * encode("aaaabcccaa") = "4a1b3c2a"
 * decode("3e4f2e") = "eeeffffee"
 */
public class RunLengthEncoding {
    public static String encode(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char cur = s.charAt(0);
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == cur) count ++;
            else {
                sb.append(count);
                sb.append(cur);
                count = 1;
                cur = s.charAt(i);
            }
            i ++;
        }
        sb.append(count);
        sb.append(cur);
        return sb.toString();
    }

    public static String decode(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (Character.isDigit(s.charAt(i))) {
                count = count * 10 + s.charAt(i) - '0';
            }
            else {
                sb.append(new String(new char[count]).replace('\0', s.charAt(i)));
                count = 0;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(encode("aaaabcccaa"));
        System.out.println(decode("13e4f2e"));
    }
}
