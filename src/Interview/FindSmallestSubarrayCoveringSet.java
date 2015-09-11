package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 9/11/15.
 * Given an array of strings and a set of strings,
 * return the indices of the starting and ending index of a shortest
 * subarray of the given array that "covers" the set.
 */
public class FindSmallestSubarrayCoveringSet {
    public int[] findIndices(String[] paragraph, Set<String> keywords) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        int left = 1, right = 1; // two pointers to scan the paragraph array
        Map<String, Integer> wordsFind = new HashMap<>();
        while (right < paragraph.length) {
            // find the subarray that covers all the keywords
            while (right < paragraph.length && wordsFind.size() != keywords.size()) {
                if (keywords.contains(paragraph[right])) {
                    if (!wordsFind.containsKey(paragraph[right])) {
                        wordsFind.put(paragraph[right], 1);
                    }
                    else {
                        int count = wordsFind.get(paragraph[right]);
                        wordsFind.put(paragraph[right], count + 1);
                    }
                }
                right ++;
            }
            if (wordsFind.size() == keywords.size() &&
               (res[0] == -1 || right - left - 1 < res[1] - res[0])) { // since last step right ++
                res[0] = left;
                res[1] = right - 1;
            }
            while (left < right && wordsFind.size() == keywords.size()) {
                if (right - left - 1 < res[1] - res[0]) {
                    res[0] = left;
                    res[1] = right - 1;
                }
                if (keywords.contains(paragraph[left])) {
                    int count = wordsFind.get(paragraph[left]);
                    if (count == 1) wordsFind.remove(paragraph[left]);
                    else {
                        wordsFind.put(paragraph[left], count - 1);
                    }
                }
                left ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] paragraph = {"apple", "banana", "apple", "apple", "dog",
        "cat", "apple", "dog", "banana", "apple", "cat", "dog"};
        Set<String> keywords = new HashSet<>();
        keywords.add("dog");
        keywords.add("apple");
        FindSmallestSubarrayCoveringSet fssc = new FindSmallestSubarrayCoveringSet();
        System.out.println(Arrays.toString(fssc.findIndices(paragraph, keywords)));
    }
}
