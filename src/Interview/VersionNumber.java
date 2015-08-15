package Interview;

/**
 * Created by yongyangyu on 12/15/14.
 */
public class VersionNumber {
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.min(v1.length, v2.length);
        for (int i = 0; i < len; i ++) {
            int n1 = Integer.parseInt(v1[i]);
            int n2 = Integer.parseInt(v2[i]);
            if (n1 > n2) {
                return 1;
            }
            else if (n1 < n2) {
                return -1;
            }
        }
        if (v1.length == v2.length) {
            return 0;
        }
        else {
            if (v1.length > v2.length) {
                for (int i = len; i < v1.length; i ++) {
                    if (Integer.parseInt(v1[i]) != 0) {
                        return 1;
                    }
                }
                return 0;
            }
            else {
                for (int i = len; i < v2.length; i ++) {
                    if (Integer.parseInt(v2[i]) != 0) {
                        return -1;
                    }
                }
                return 0;
            }
        }
    }

    public static void main(String [] args) {
        System.out.println(compareVersion("1.0", "1"));
    }
}
