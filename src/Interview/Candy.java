package Interview;

/**
 * Created by yongyangyu on 10/3/15.
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:

 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Candy {
    public int candy(int[] ratings) {
        int[] num = new int[ratings.length];
        num[0] = 1;
        // scan forward
        for (int i = 1; i < num.length; i ++) {
            if (ratings[i] > ratings[i-1])
                num[i] = num[i-1] + 1;
            else
                num[i] = 1;
        }
        // scan backward
        for (int i = num.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i+1] && num[i] <= num[i+1])
                num[i] = num[i+1] + 1;
        }
        int count = 0;
        for (int n: num)
            count += n;
        return count;
    }

    public static void main(String[] args) {
        int[] ratings = {5, 3, 1};
        new Candy().candy(ratings);
    }
}
