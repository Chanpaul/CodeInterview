package Interview;

/**
 * Created by yongyangyu on 12/3/14.
 */
public class RabinKarp {
    public static int strStr(String T, String P) {
        int base = 29;
        long phash = 0;
        if (T.length() < P.length()) {
            return -1;
        }
        long thash = 0;
        for (int i = 0; i < P.length(); i ++) {
            phash = phash * base + new Character(P.charAt(i)).hashCode();
            thash = thash * base + new Character(T.charAt(i)).hashCode();
        }
        if (thash == phash) {
            return 0;
        }
        else {
            for (int i = 1; i < T.length(); i ++) {
                thash -= new Character(T.charAt(i - 1)).hashCode() * Math.pow(base, P.length() - 1);
                thash = thash * base + new Character(T.charAt(i + P.length() - 1)).hashCode();
                if (thash == phash) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String T = "bacbabababacaab";
        String P = "ababaca";
        System.out.println(strStr(T, P));
    }
}
