package Interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongyangyu on 11/1/15.
 * Given a nested list of integers, returns the sum of all integers in the list weighted by
 * their depth. e.g., [[1, 1], 2, [1, 1]] returns 10 since four 1's at depth 2 and one 2 at
 * depth 1.
 */
public class NestedIntegerList {
    public int depthSum(List<NestedInteger> input) {
        return depthHelper(input, 1);
    }

    private int depthHelper(List<NestedInteger> input, int depth) {
        int res = 0;
        for (NestedInteger ni: input) {
            if (ni.isInteger()) res += depth * ni.getInteger();
            else {
                res += depthHelper(ni.getList(), depth + 1);
            }
        }
        return res;
    }

    public static class NestedIntegerImpl implements NestedInteger {
        private int val;
        private List<NestedInteger> list;

        public NestedIntegerImpl() {
            val = 0;
            list = null;
        }
        public NestedIntegerImpl(int val) {
            this.val = val;
            this.list = null;
        }

        public NestedIntegerImpl(List<NestedInteger> list) {
            val = 0;
            this.list = list;
        }

        @Override
        public boolean isInteger() {
           if (list == null) return true;
           return false;
        }

        @Override
        public Integer getInteger() {
            return val;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }

    public static void main(String[] args) {
        //[1, [4, [6]]]
        List<NestedInteger> l1 = new ArrayList<>();
        l1.add(new NestedIntegerImpl(6));
        NestedIntegerImpl n1 = new NestedIntegerImpl(l1);
        List<NestedInteger> l2 = new ArrayList<>();
        l2.add(new NestedIntegerImpl(4));
        l2.add(n1);
        NestedIntegerImpl n2 = new NestedIntegerImpl(l2);
        List<NestedInteger> l3 = new ArrayList<>();
        l3.add(new NestedIntegerImpl(1));
        l3.add(n2);
        NestedIntegerList nil = new NestedIntegerList();
        System.out.println(nil.depthSum(l3));
    }
}
