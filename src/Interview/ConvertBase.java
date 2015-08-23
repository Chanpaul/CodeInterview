package Interview;

/**
 * Created by yongyangyu on 8/23/15.
 * Convert an integer in base b1 to base b2.
 * 2 <= b1, b2 <= 16 and suppose the input string contains all digits or capital letters.
 */
public class ConvertBase {
    public static String convert(String s, int b1, int b2) {
        if (b1 == b2) return s;
        int value = 0;
        boolean negative = false;
        for (int i = 0; i < s.length(); i ++) {
            if (i == 0 && s.charAt(i) == '-') negative = true;
            else {
                value *= b1;
                value += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            int tmp = value % b2;
            char ch = (char) ((tmp < 10) ? tmp + '0' : tmp - 10 + 'A');
            sb.append(ch);
            value /= b2;
        }
        if (negative) sb.append('-');
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("615", 7, 13));
    }
}
