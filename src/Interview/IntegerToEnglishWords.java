package Interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 8/31/15.
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 2^31 - 1.
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Hint:
 *
 * 1. Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 * 2. Group the number by thousands (3 digits). You can write a helper function that takes a number less
 * than 1000 and convert just that chunk to words.
 * 3. There are many edge cases. What are some good test cases? Does your code work with input such as 0?
 * Or 1000010? (middle chunk is zero and should not be printed out)
 */
public class IntegerToEnglishWords {
    private static final Map<Integer, String> digitMap = new HashMap<>();
    private static final Map<Integer, String> posToWords = new HashMap<>();

    public IntegerToEnglishWords() {
        digitMap.put(0, "");
        digitMap.put(1, "One");
        digitMap.put(2, "Two");
        digitMap.put(3, "Three");
        digitMap.put(4, "Four");
        digitMap.put(5, "Five");
        digitMap.put(6, "Six");
        digitMap.put(7, "Seven");
        digitMap.put(8, "Eight");
        digitMap.put(9, "Nine");
        digitMap.put(10, "Ten");
        digitMap.put(11, "Eleven");
        digitMap.put(12, "Twelve");
        digitMap.put(13, "Thirteen");
        digitMap.put(14, "Fourteen");
        digitMap.put(15, "Fifteen");
        digitMap.put(16, "Sixteen");
        digitMap.put(17, "Seventeen");
        digitMap.put(18, "Eighteen");
        digitMap.put(19, "Nineteen");
        digitMap.put(20, "Twenty");
        digitMap.put(30, "Thirty");
        digitMap.put(40, "Forty");
        digitMap.put(50, "Fifty");
        digitMap.put(60, "Sixty");
        digitMap.put(70, "Seventy");
        digitMap.put(80, "Eighty");
        digitMap.put(90, "Ninety");
        posToWords.put(0, "");
        posToWords.put(1, "Thousand");
        posToWords.put(2, "Million");
        posToWords.put(3, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        Deque<String> stack = new ArrayDeque<>();
        do {
            stack.push(numLen3(num % 1000));
            num /= 1000;
        } while (num > 0);

        StringBuilder sb = new StringBuilder();
        while (stack.size() != 0) {
            String tmp = null;
            if (stack.peek().length() > 0) {
                if (sb.length() != 0) {
                    sb.append(" ");
                }
            }
            tmp = stack.pop();
            if (tmp.length() != 0) {
                sb.append(tmp + " " + posToWords.get(stack.size()));
            }
        }
        return sb.toString().trim();
    }

    private String numLen3(int num) {
        if (num > 999 || num == 0) return "";
        num %= 1000;

        if (num <= 20) return digitMap.get(num);
        else {
            Deque<String> stack = new ArrayDeque<>();
            if ((num % 100) <= 20) {
                stack.push(digitMap.get(num % 100));
                num /= 100;
            }
            else {
                stack.push(digitMap.get(num % 10));
                num /= 10;
                if (num != 0) stack.push(digitMap.get((num % 10) * 10));
                num /= 10;
            }
            if (num != 0) {
                stack.push(digitMap.get(num) + " Hundred");
            }
            StringBuilder sb = new StringBuilder();
            while (stack.size() != 0) {
                if (stack.peek().length() > 0) {
                    if (sb.length() != 0) {
                        sb.append(" ");
                    }
                }
                sb.append(stack.pop());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        IntegerToEnglishWords i2e = new IntegerToEnglishWords();
        System.out.println(i2e.numberToWords(00));
        System.out.println(i2e.numberToWords(123));
        System.out.println(i2e.numberToWords(98));
        System.out.println(i2e.numberToWords(205));
        System.out.println(i2e.numberToWords(123000));
        System.out.println(i2e.numberToWords(12345));
        System.out.println(i2e.numberToWords(1234567));
        System.out.println(i2e.numberToWords(111));
    }
}
