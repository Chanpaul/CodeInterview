package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/14/15.
 * Problem: Search for the largest recurring substring in the text.
 * This problem comes from Section 15.2 of "Programming Pearls (2nd Edition)".
 * The solution utilizes the idea of suffix array and the implementation
 * referencing from the textbook "Algorithms by Robert Sedgewick".
 */
public class SuffixArray {
    private Suffix[] suffixes;

    public SuffixArray(String text) {
        suffixes = new Suffix[text.length()];
        for (int i = 0; i < text.length(); i ++) {
            suffixes[i] = new Suffix(text, i);
        }
    }

    // return indices of the longest recurring substring
    public int[] longestRecurringSubstring() {
        Arrays.sort(suffixes);
        int[] res = new int[2];
        for (int i = 0; i < suffixes.length - 1; i ++) {
            int[] tmp = suffixes[i].longestPrefix(suffixes[i+1]);
            if (tmp[1] - tmp[0] > res[1] - res[0]) {
                res[0] = tmp[0];
                res[1] = tmp[1];
            }
        }
        return res;
    }

    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length() - index;
        }

        private char charAt(int i) {
            return text.charAt(index + i);
        }

        // return the indices of the longest prefix
        private int[] longestPrefix(Suffix other) {
            int n = Math.min(this.length(), other.length());
            int size = 0;
            for (int i = 0; i < n; i ++) {
                if (this.charAt(i) == other.charAt(i)) size ++;
                else break;
            }
            return new int[]{index, index + size};
        }

        @Override
        public int compareTo(Suffix other) {
            if (this == other) return 0;
            int n = Math.min(this.length(), other.length());
            for (int i = 0; i < n; i ++) {
                if (this.charAt(i) < other.charAt(i)) return -1;
                if (this.charAt(i) > other.charAt(i)) return 1;
            }
            return this.length() - other.length();
        }

        @Override
        public String toString() {
            return text.substring(index);
        }
    }

    public static void main(String[] args) {
        String text = "Ask not what your country can do for you, but " +
                "what you can do for your country";
        SuffixArray sa = new SuffixArray(text);
        int[] indices = sa.longestRecurringSubstring();
        System.out.println("\"" + text.substring(indices[0], indices[1]) + "\"");
    }
}
