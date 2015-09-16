package Interview;

/**
 * Created by yongyangyu on 9/15/15.
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 *
 * Note: The numbers can be arbitrarily large and are non-negative.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        StringBuilder n1 = new StringBuilder(num1);
        StringBuilder n2 = new StringBuilder(num2);
        n1.reverse();
        n2.reverse();
        for (int i = 0; i < n2.length(); i ++) {
            if (n2.charAt(i) - '0' == 0) continue;
            int outer = n2.charAt(i) - '0';
            int carry = 0;
            int j;
            for (j = 0; j < n1.length(); j ++) {
                int inner = n1.charAt(j) - '0';
                int digit = (outer * inner + carry) % 10; // compute digit first
                carry = (outer * inner + carry) / 10; // then update carry
                carry += (digit + res[i+j]) / 10;
                res[i+j]= (digit + res[i+j]) % 10;
            }
            if (carry > 0) {
                res[i+j] = carry;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int x : res) {
            sb.append(x);
        }
        sb.reverse();
        int cutoff = 0;
        for(int i = 0; i < sb.length(); i ++) {
            if (sb.charAt(i) == '0') continue;
            else {
                cutoff = i;
                break;
            }
        }
        if (sb.charAt(cutoff) == '0') return "0";
        return sb.substring(cutoff);
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply(num1, num2));
    }
}
