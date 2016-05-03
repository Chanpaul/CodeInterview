package Interview;

/**
 * Created by yongyangyu on 5/3/16.
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 */
public class powerOfFour {
    public boolean isPowerOfFour(int num) {
        // check if num is a power of 2
        // then check specific positions to exclude odd powers of 2
        if ((num > 0) && ((num & (num-1)) == 0) && ((num & 0x55555555) != 0))
            return true;
        return false;
    }
}
