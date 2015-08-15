package Interview;

/**
 * Created by yongyangyu on 12/1/14.
 */
public class Hanoi {
    public static String hanoi(int nDisk, int fromPeg, int toPeg) {
        int helpPeg;
        String sol1, sol2, currStep;
        if (nDisk == 1) {
            return fromPeg + " -> " + toPeg + "\n";
        }
        helpPeg = 6 - fromPeg - toPeg; // peg id ranges from 1 to 3, so the sum of ids is 6
        sol1 = hanoi(nDisk-1, fromPeg, helpPeg);
        currStep = fromPeg + " -> " + toPeg + "\n";
        sol2 = hanoi(nDisk-1, helpPeg, toPeg);
        return sol1 + currStep + sol2;
    }

    public static void main(String[] args) {
        System.out.println(hanoi(3, 1, 3));
    }
}
