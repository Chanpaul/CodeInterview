package Interview;

/**
 * Created by yongyangyu on 11/17/15.
 * Additive number is a positive integer whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Given a string represents an integer, write a function to determine if it's an additive number.
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n/2; i ++) {
            Long x1 = Long.parseLong(num.substring(0, i)); // look for the 1st number
            for (int j = 1; Math.max(i, j) <= n-i-j; j++) {
                if (num.charAt(i) == '0' && j > 1) break;
                Long x2 = Long.parseLong(num.substring(i, i+j)); // look for the 2nd number
                if (isValid(x1, x2, j+i, num)) return true;
            }
        }
        return false;
    }

    private boolean isValid(Long x1, Long x2, int start, String num) {
        if (start == num.length()) return true; // base case for recursion
        Long x3 = x1 + x2;
        String expected = x3.toString();
        int end = start + expected.length();
        if (end > num.length()) return false; // current x1 and x2 not valid
        String actual = num.substring(start, end);
        return expected.equals(actual) && isValid(x2, x3, end, num);
    }

    public static void main(String[] args) {
        AdditiveNumber an = new AdditiveNumber();
        System.out.println(an.isAdditiveNumber("112358"));
        System.out.println(an.isAdditiveNumber("199100199"));
    }
}
