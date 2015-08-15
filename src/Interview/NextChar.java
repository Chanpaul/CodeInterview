package Interview;

/**
 * Created by yongyangyu on 11/18/14.
 */
public class NextChar {
    public static char getNextChar(char[] sortedStr, char c) {
        char rval = 0;
        if (c > sortedStr[sortedStr.length - 1]) {
            return sortedStr[0];
        }
        int l = 0, r = sortedStr.length - 1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (l == mid) {
                if (sortedStr[l] > c) {
                    return sortedStr[l];
                }
                else if (sortedStr[l] < c) {
                    return sortedStr[l+1];
                }
            }
            if (sortedStr[mid] == c) {
                return sortedStr[mid+1];
            }
            else if (sortedStr[mid] > c) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return rval;
    }

    public static void main(String[] args) {
        System.out.println(getNextChar(new char[]{'c','f','p','v'}, 'a'));  // 'c'
        System.out.println(getNextChar(new char[]{'c','f','p','v'}, 'c')); // 'f'
        System.out.println(getNextChar(new char[]{'c','f','p','v'}, 'k')); //'p'
        System.out.println(getNextChar(new char[]{'c','f','p','v'}, 'z')); // 'c'
        System.out.println(getNextChar(new char[]{'c','f','k'}, 'f')); //'k'
        System.out.println(getNextChar(new char[]{'c','f','k'}, 'c')); //'f'
        System.out.println(getNextChar(new char[]{'c', 'f', 'k'}, 'd')); // 'f'
    }
}
