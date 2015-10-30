package Interview;

/**
 * Created by yongyangyu on 9/15/15.
 * updated on 10/29/15
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for (int i = 0; i < num2.length(); i ++) {
            if (num2.charAt(num2.length()-i-1) - '0' == 0) continue;
            int outer = num2.charAt(num2.length()-i-1) - '0';
            int len = num1.length() + num2.length();
            int carry = 0;
            int j;
            for (j = 0; j < num1.length(); j ++) {
                int inner = num1.charAt(num1.length()-j-1) - '0';
                int digit = (outer * inner + carry) % 10; // compute digit first
                carry = (outer * inner + carry) / 10; // then update carry
                carry += (digit + res[len-i-j-1]) / 10;
                res[len-i-j-1]= (digit + res[len-i-j-1]) % 10;
            }
            if (carry > 0) {
                res[len-i-j-1] = carry;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int x : res) {
            if (x == 0 && sb.length() == 0) continue;
            sb.append(x);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply(num1, num2));
    }
}
