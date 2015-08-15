package Interview;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongyangyu on 3/24/15.
 */
public class Tuple2<T1, T2> {
    private final T1 field1;
    private final T2 field2;

    public Tuple2(T1 t1, T2 t2) {
        field1 = t1;
        field2 = t2;
    }

    public T1 _1() {
        return field1;
    }

    public T2 _2() {
        return field2;
    }

    @Override public boolean equals(Object other) {
        if (!(other instanceof Tuple2)) {
            return false;
        }
        Tuple2 o = (Tuple2)other;
        if (field1.getClass() == o._1().getClass() && field2.getClass() == o._2().getClass()) {
            return field1.equals(o._1()) && field2.equals(o._2());
        }
        return false;
    }

    @Override public int hashCode() {
        int rval = 17;
        rval = rval * 31 + field1.hashCode();
        rval = rval * 31 + field2.hashCode();
        return rval;
    }

    public static void main(String[] args) {
        Tuple2<Integer, String> t1 = new Tuple2<>(1, "one");
        Tuple2<Integer, String> t2 = new Tuple2<>(2, "two");
        System.out.println(t1._1());
        System.out.println(t1._2());
        System.out.println(t1.equals(t2));
        Set<Tuple2<Integer, String>> myset = new HashSet<>();
        myset.add(t1);
        myset.add(t2);
        System.out.println(myset.contains(t1));
        System.out.println(myset.contains(t2));
    }
}
