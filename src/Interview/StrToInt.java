package Interview;

/**
 * Created by yongyangyu on 12/6/14.
 */
public class StrToInt {
    public static int atoi(String str) {
        String s = str.trim();
        int x = 0;
        boolean positive = true;
        if (s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '-') {
            positive = false;
        }
        else if (s.charAt(0) == '+') {
            positive = true;
        }
        else {
            if (s.charAt(0) < '0' || s.charAt(0) > '9') {
                return 0;
            }
            else {
                x = s.charAt(0) - '0';
            }
        }
        for (int i = 1; i < s.length(); i ++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int y = x * 10 + s.charAt(i) - '0';
                if (x != (y - (s.charAt(i) - '0')) / 10) {
                    if (positive) {
                        return Integer.MAX_VALUE;
                    }
                    else {
                        return Integer.MIN_VALUE;
                    }
                }
                x = y;
            }
            else {
                break;
            }
        }
        if (!positive) {
            x = -x;
        }
        if (positive && x < 0) {
            return Integer.MAX_VALUE;
        }
        if (!positive && x > 0) {
            return Integer.MIN_VALUE;
        }
        return x;
    }

    public static void main(String[] args) {
        String str = "    +1";
        System.out.println(atoi(str));
    }
}
