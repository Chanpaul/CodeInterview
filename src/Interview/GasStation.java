package Interview;

/**
 * Created by yongyangyu on 9/8/15.
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
 * its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 1) { // a circle consisting of one station
            if (gas[0] >= cost[0]) return 0;
            return -1;
        }
        int start = 0; // starting station index
        int count = n - 1; // number of stations still needs to cover
        int circle = n; // from start, size of circle to finish
        int i = (start + 1) % n; // next start station
        int tank = gas[start];
        while (circle != 0 && count != -1) {
            tank -= cost[(i + n - 1) % n];
            if (tank < 0) {
                start = i % n;
                tank = gas[start];
                i = (start + 1) % n;
                count --;
                circle = n;
            }
            else {
                tank += gas[i];
                i = (i + 1) % n;
                circle --;
            }
        }
        if (count == -1) return -1;
        return start;
    }
}
