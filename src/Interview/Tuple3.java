package Interview;

/**
 * Created by yongyangyu on 3/24/15.
 */
public class Tuple3<T1, T2, T3> {
    private final T1 field1;
    private final T2 field2;
    private final T3 field3;

    public Tuple3(T1 t1, T2 t2, T3 t3) {
        field1 = t1;
        field2 = t2;
        field3 = t3;
    }

    public T1 _1() {
        return field1;
    }

    public T2 _2() {
        return field2;
    }

    public T3 _3() {
        return field3;
    }
}
