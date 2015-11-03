package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 11/2/15 (from LinkedIn).
 * Design a data structure that can add, remove, and removeRand in O(1) time.
 * No need to keep the order of the elements added.
 */
public class AddRemoveConstTime<T> {
    private List<T> buffer;
    private Map<T, Integer> valToPos;

    public AddRemoveConstTime() {
        buffer = new LinkedList<>();
        valToPos = new HashMap<>();
    }

    public boolean add(T t) {
        if (valToPos.containsKey(t)) return false;
        else {
            buffer.add(t);
            valToPos.put(t, buffer.size() - 1);
            return true;
        }
    }

    public boolean remove(T t) {
        if (!valToPos.containsKey(t)) return false;
        else {
            // do a swap with T and the last element
            int pos = valToPos.get(t);
            valToPos.remove(t);
            buffer.remove(pos);
            T last = buffer.get(buffer.size() - 1);
            valToPos.put(last, pos);
            buffer.add(pos, last);
            buffer.remove(buffer.size() - 1);
            return true;
        }
    }

    public T removeRandom() {
        Random rand = new Random();
        int pos = Math.abs(rand.nextInt()) % buffer.size();
        System.out.println("pos = " + pos);
        T res = buffer.get(pos);
        remove(res);
        return res;
    }

    public static void main(String[] args) {
        AddRemoveConstTime<Integer> test = new AddRemoveConstTime<>();
        test.add(1); test.add(2); test.add(3); test.add(4);
        test.remove(2);
        System.out.println(test.removeRandom());
    }
}
