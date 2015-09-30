package Interview;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by yongyangyu on 3/16/15.
 */
public class Subset {
    public static void generate(HashSet<Integer> x, HashSet<HashSet<Integer>> rval) {
        if (x.size() == 0) {
            return;
        }
        if (!rval.contains(x)) {
            rval.add(new HashSet<>(x));
        }
        Iterator<Integer> iter = x.iterator();
        while (iter.hasNext()) {
            Integer curr = iter.next();
            HashSet<Integer> tmp = new HashSet<>(x);
            tmp.remove(curr);
            generate(tmp, rval);
            tmp.add(curr);
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> x = new HashSet<>();
        x.add(1);
        x.add(2);
        x.add(3);
        HashSet<HashSet<Integer>> rval = new HashSet<>();
        generate(x, rval);
        System.out.println(rval.size());
    }
}
