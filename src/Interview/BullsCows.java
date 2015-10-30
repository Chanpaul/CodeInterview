package Interview;

import java.util.*;
/**
 * Created by yongyangyu on 10/30/15.
 * You are playing the following Bulls and Cows game with your friend:
 * You write a 4-digit secret number and ask your friend to guess it,
 * each time your friend guesses a number, you give a hint, the hint tells your
 * friend how many digits are in the correct positions (called "bulls") and
 * how many digits are in the wrong positions (called "cows"), your friend will
 * use those hints to find out the secret number.
 *
 * For example:
 *
 * Secret number:  1807
 * Friend's guess: 7810
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess,
 * use A to indicate the bulls and B to indicate the cows, in the above example,
 * your function should return 1A3B.
 *
 * You may assume that the secret number and your friend's guess only contain digits,
 * and their lengths are always equal.
 */
public class BullsCows {
    public String getHind(String secret, String guess) {
        int bulls = 0;
        Map<Character, Integer> char2count = new HashMap<>();
        for (int i = 0; i < secret.length(); i ++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls ++;
            if (!char2count.containsKey(secret.charAt(i))) {
                char2count.put(secret.charAt(i), 1);
            }
            else {
                int count = char2count.get(secret.charAt(i));
                char2count.put(secret.charAt(i), count+1);
            }
        }
        int cows = 0;
        for (int i = 0; i < guess.length(); i ++) {
            if (char2count.containsKey(guess.charAt(i))) {
                cows ++;
                int count = char2count.get(guess.charAt(i)) - 1;
                if (count == 0) char2count.remove(guess.charAt(i));
                else char2count.put(guess.charAt(i), count);
            }
        }
        return bulls + "A" + (cows-bulls) + "B";
    }

    public static void main(String[] args) {
        BullsCows bc = new BullsCows();
        System.out.println(bc.getHind("1234", "0111"));
    }
}
