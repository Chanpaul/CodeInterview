package Interview;

/**
 * Created by yongyangyu on 6/21/15.
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows 
 * like this: (you may want to display this pattern in a fixed font for better legibility)

 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:

 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZag {
    public String convert(String s, int numRows) {
        StringBuilder[] arr = new StringBuilder[numRows];
        int idx = 0;
        boolean up = true;
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = new StringBuilder();
        }
        for(int i = 0; i < s.length(); i ++) {
            if (up) {
                arr[idx].append(s.charAt(i));
                idx ++;
                if (idx == numRows) {
                    up = false;
                    idx = Math.max(numRows - 2, 0);
                }
            }
            else {
                arr[idx].append(s.charAt(i));
                idx --;
                if (idx == -1) {
                    up = true;
                    idx = Math.min(1, numRows - 1);
                }
            }
        }
        StringBuilder res = arr[0];
        for (int i = 1; i < arr.length; i ++) {
            res = res.append(arr[i].toString());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(new ZigZag().convert(s, 3));
    }
}
