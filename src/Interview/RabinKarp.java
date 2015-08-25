package Interview;

/**
 * Created by yongyangyu on 12/3/14.
 * Refactored to better compute powers of the base.
 */
public class RabinKarp {
    static final int base = 26;
    static final int mod = 997;
    public static int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;  // needle cannot be a substring
        int hayHash = 0, nhash = 0;
        int power = 1;
        for (int i = 0; i < needle.length(); i ++) {
            power = i != 0 ? power * base % mod : 1;
            hayHash = (hayHash * base + haystack.charAt(i)) % mod;
            nhash = (nhash * base + needle.charAt(i)) % mod;
        }

        for (int i = needle.length(); i < haystack.length(); i ++) {
            if (hayHash == nhash && needle.equals(haystack.substring(i - needle.length(), i))) {
                return i - needle.length();
            }
            hayHash -= (power * haystack.charAt(i - needle.length())) % mod;
            if (hayHash < 0) hayHash += mod;
            hayHash = (hayHash * base + haystack.charAt(i)) % mod;
        }
        if (hayHash == nhash && needle.equals(haystack.substring(haystack.length() - needle.length()))) {
            return haystack.length() - needle.length();
        }

        return -1;
    }

    public static void main(String[] args) {
        String haystack = "bacbabababacaab";
        String needle = "ababaca";
        System.out.println(strStr(haystack, needle));
    }
}
