package Interview;

/**
 * Created by yongyangyu on 11/18/14.
 */
public class WordDistanceFinder {
    private String[] data;
    public WordDistanceFinder(String[] data) {
        this.data = new String[data.length];
        for (int i = 0; i < data.length; i ++) {
            this.data[i] = data[i];
        }
    }
    public int distance(String s1, String s2) {
        int dist = data.length;
        int last1 = -1;
        int last2 = -1;
        for (int i = 0; i < data.length; i ++) {
            if (data[i].equals(s1)) {
                last1 = i;
            }
            if (data[i].equals(s2)) {
                last2 = i;
            }
            if (data[i].equals(s1) || data[i].equals(s2) && (last1 != -1 && last2 != -1)) {
                dist = Math.min(dist, Math.abs(last1 - last2));
            }
        }
        return dist;
    }

    public static void main(String [] args) {
        WordDistanceFinder wdf = new WordDistanceFinder(new String[]{"the", "quick", "brown", "fox", "quick"});
        System.out.println(wdf.distance("fox", "the"));
    }
}
