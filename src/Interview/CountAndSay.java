package Interview;

/**
 * Created by yongyangyu on 12/2/14.
 */
public class CountAndSay {
    public static String say(int n) {
        if (n <= 0) {
            return null;
        }
        String res = "1";
        // assgin initial value
        n --;
        if (n == 0) {
            return res;
        }
        else {
            while (n > 0) {
                StringBuilder sb = new StringBuilder();
                int count = 1;
                char last = res.charAt(0);
                for (int i = 1; i < res.length(); i ++) {
                    if (res.charAt(i) == last) {
                        count ++;
                    }
                    else {
                        sb.append(count);
                        sb.append(last);
                        count = 1;
                        last = res.charAt(i);
                    }
                }
                // don't forget the last step
                sb.append(count);
                sb.append(last);
                res = sb.toString();
                n --;
            }
            return res;
        }
    }

    public static void main(String [] args) {
        for (int i = 1; i <= 10; i ++) {
            System.out.println(i + ": " + say(i));
        }
    }
}
