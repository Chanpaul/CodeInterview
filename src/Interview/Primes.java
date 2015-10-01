package Interview;

/**
 * Created by yongyangyu on 4/29/15.
 */
public class Primes {
    public static int countPrimes(int n) {
        int count = 0;
        boolean[] sieve = new boolean[n];
        for(int i = 2; i < sieve.length; i++){
            if(!sieve[i]){
                count++;
                for(int j = 2*i; j < sieve.length; j = j + i){
                    sieve[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 30;//999983;
        System.out.println(countPrimes(n));
    }
}
