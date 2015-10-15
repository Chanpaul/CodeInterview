package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yongyangyu on 1/11/15.
 */
public class FlattenIterator {
    public static Iterator<String> flatten(Iterator<Iterator<String>> iters) {
        final Iterator<Iterator<String>> myIter = iters;
        return new Iterator<String>() {
            private Iterator<String> curr = initCurr();

            private Iterator<String> initCurr() {
                while(myIter != null && myIter.hasNext()) {
                    Iterator<String> tmp = myIter.next();
                    if (tmp != null && tmp.hasNext()) {
                        return tmp;
                    }
                }
                return null;
            }
            @Override
            public boolean hasNext() {
                if (curr != null && curr.hasNext()) {
                    return true;
                }
                else {
                    while (myIter != null && myIter.hasNext()) {
                        Iterator<String> tmp = myIter.next();
                        if (tmp != null && tmp.hasNext()) {
                            curr = tmp;
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public String next() {
                if (curr.hasNext()) {
                    return curr.next();
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    public static void main(String[] args) throws Exception {
        List<Iterator<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("a", "b" ,"c").iterator());
        lists.add(null);
        lists.add(Arrays.<String>asList().iterator());
        lists.add(Arrays.<String>asList().iterator());
        lists.add(Arrays.asList("d" , "e").iterator());
        lists.add(Arrays.<String>asList().iterator());

        Iterator<Iterator<String>> iters = lists.iterator();
        Iterator<String> flattened = flatten(iters);
        while(flattened.hasNext()) {
            System.out.println(flattened.next());
        }
    }
}
