package Interview;

import java.util.PriorityQueue;

/**
 * Created by yongyangyu on 12/3/15.
 * Write a program to find the nth super ugly number.
 *
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime
 * list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the
 * sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 *
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000.
 */
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ugly[0] = 1;
        int[] ptr = new int[primes.length];
        for (int i = 1; i < n; i ++) {
            pq.clear();
            while (ugly[i] == 0 || ugly[i] == ugly[i-1]) {
                for (int j = 0; j < primes.length; j ++) {
                    pq.add(ugly[ptr[j]] * primes[j]);
                }
                ugly[i] = pq.peek();
                for (int j = 0; j < primes.length; j ++) {
                    if (ugly[i] == ugly[ptr[j]] * primes[j]) {
                        ptr[j] ++;
                    }
                }
            }
        }
        return ugly[n-1];
    }

    public static void main(String[] args) {
        int[] primes = {2,7,13,19};
        SuperUglyNumber sun = new SuperUglyNumber();
        System.out.println(sun.nthSuperUglyNumber(12, primes));
    }
}
