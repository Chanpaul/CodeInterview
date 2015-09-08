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
        int numAsStart = n - 1;
        int curCircleLen = n;
        int i = (start + 1) % n; // next start station
        int tank = gas[start];
        while (curCircleLen != 0 && numAsStart != -1) {
            tank -= cost[(i + n - 1) % n];
            if (tank < 0) {
                start = i % n;
                tank = gas[start];
                i = (start + 1) % n;
                numAsStart --;
                curCircleLen = n;
            }
            else {
                tank += gas[i];
                i = (i + 1) % n;
                curCircleLen --;
            }
        }
        if (numAsStart == -1) return -1;
        return start;
    }
}
