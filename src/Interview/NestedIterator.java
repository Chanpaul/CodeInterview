package Interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by yongyangyu on 5/2/16.
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 *
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Given the list [1,[4,[6]]],
 *
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
       for (int i = nestedList.size() - 1; i >= 0; i --) {
           stack.push(nestedList.get(i));
       }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        while (!stack.isEmpty()) {
            if (stack.peek() == null)  {
                stack.pop();
            }
            else if (stack.peek().isInteger()) {
                return true;
            }
            else if (stack.peek().getList() == null || stack.peek().getList().isEmpty()) {
                stack.pop();
            }
            else {
                // current element is a list, unwrap it
                List<NestedInteger> list = stack.pop().getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }
        return !stack.isEmpty();
    }

    // add a test case class
    public static class NestIntegerImpl implements NestedInteger {
        private Integer x;
        private List<NestedInteger> list;
        public NestIntegerImpl(int x) {
            this.x = x;
            this.list = null;
        }

        public NestIntegerImpl(List<NestedInteger> list) {
            this.x = null;
            this.list = list;
        }

        @Override
        public Integer getInteger() {
            return x;
        }

        @Override
        public boolean isInteger() {
            return x != null;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestIntegerImpl(1));
        List<NestedInteger> x3 = new ArrayList<>();
        x3.add(new NestIntegerImpl(6));
        List<NestedInteger> x2 = new ArrayList<>();
        x2.add(new NestIntegerImpl(4));
        x2.add(new NestIntegerImpl(x3));
        list.add(new NestIntegerImpl(x2));
        NestedIterator niter = new NestedIterator(list);
        while (niter.hasNext()) {
            System.out.println(niter.next());
        }
    }
}


