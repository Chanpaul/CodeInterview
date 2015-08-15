package Interview;

/**
 * Created by yongyangyu on 6/23/15.
 */
public class Numbers {
    public static int reverseInt(int x) {
        boolean pos = true;
        if (x < 0) pos = false;
        long tmp = (long)x;
        if (!pos) tmp = -tmp;
        StringBuilder sb = new StringBuilder(new Long(tmp).toString());
        long rval = Long.parseLong(sb.reverse().toString());
        if (rval > Integer.MAX_VALUE) {
            return 0;
        }
        if (!pos) {
            return -(int)rval;
        }
        else {
            return (int)rval;
        }
    }

    /*
     * Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge,
please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
You are responsible to gather all the input requirements up front.

        The function first discards as many whitespace characters as necessary until
        the first non-whitespace character is found. Then, starting from this character,
        takes an optional initial plus or minus sign followed by as many numerical digits as possible,
        and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number,
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number,
or if no such sequence exists because either str is empty or it contains only whitespace characters,
no conversion is performed.

If no valid conversion could be performed, a zero value is returned.
If the correct value is out of the range of representable values,
INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     */
    public static int atoi(String str) {
        String s = str.trim();  // remove white spaces
        int x = 0;
        boolean pos = true;
        if (s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '-') {
            pos = false;
        }
        else if (s.charAt(0) == '+') {
            pos = true;
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
            if (Character.isDigit(s.charAt(i))) {
                // avoid overflow, use long type to represent the temporary number
                long y = (long)x * 10 + s.charAt(i) - '0';
                if (y > Integer.MAX_VALUE) {
                    if (pos) {
                        return Integer.MAX_VALUE;
                    }
                    else {
                        return Integer.MIN_VALUE;
                    }
                }
                x = (int)y;
            }
            else {
                break;
            }
        }
        if (!pos) {
            x = -x;
        }
        return x;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {     // negative numbers cannot be palindrome
            return false;
        }
        int y = x;
        int numDigits = 1;
        while (y >= 10) {
            numDigits ++;
            y /= 10;
        }
        if (numDigits == 1) {
            return true;
        }
        else {
            int power = (int)Math.pow(10, numDigits - 1);
            while (x >= 10) {
                int last = x % 10;
                int first = x / power;
                if (first != last) {
                    return false;
                }
                x %= power;     // remove first digit
                x /= 10;        // remove last digit
                numDigits -= 2;
                power /= 100;   // modify power accordingly
            }
            if (numDigits > 1) { // deal with the case 10001
                if (x == 0) {   // all the numbers in the middle are 0's
                    return true;
                }
                return false;  // case 1000021, x != 0 but exit the while loop
            }
            return true;
        }
    }

    public static boolean isPalindromeStr(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        String r = new StringBuilder(s).reverse().toString();
        return s.equals(r);
    }

    public static void main(String[] args) {
        System.out.println(atoi("2147483648"));
        long start = System.nanoTime();
        System.out.println(isPalindrome(1000021));
        long end = System.nanoTime();
        System.out.println((end - start) / 1000);
        start = System.nanoTime();
        System.out.println(isPalindromeStr(1000021));
        end = System.nanoTime();
        System.out.println((end - start) / 1000);
        start = System.nanoTime();
        System.out.println(isPalindrome(10001));
        end = System.nanoTime();
        System.out.println((end - start) / 1000);
        start = System.nanoTime();
        System.out.println(isPalindrome(10001));
        end = System.nanoTime();
        System.out.println((end - start) / 1000);
    }
}
