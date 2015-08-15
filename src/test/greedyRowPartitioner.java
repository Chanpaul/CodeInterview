package test;

import java.util.*;

/**
 * Created by yongyangyu on 6/30/15.
 */

public class greedyRowPartitioner {
    static class Tuple2 {
        private long _1;
        private int _2;
        Tuple2(long f1, int f2) {
            _1 = f1;
            _2 = f2;
        }
        long _1(){
            return _1;
        }
        int _2(){
            return _2;
        }
    }

    public static Map<Integer, Set<Long>> greedPartition(Tuple2[][] all, long paritionSize) {
        Map<Integer, Set<Long>> partitionMap = new HashMap<>();
        int parId = 0;
        long capacity = 0L;
        Set<Long> holder = new HashSet<>();
        for (int i = 0; i < all.length; i ++) {
            for (int j = 0; j < all[i].length; j ++) {
                if (capacity + all[i][j]._2() <= paritionSize) {
                    capacity += all[i][j]._2;
                    holder.add(all[i][j]._1());
                }
                else {
                    if (!holder.isEmpty()) {
                        partitionMap.put(parId, holder);
                        parId ++;
                    }
                    holder = new HashSet<>();
                    holder.add(all[i][j]._1());
                    capacity = all[i][j]._2();
                }
            }
        }
        partitionMap.put(parId, holder);
        return partitionMap;
    }

    public static void main(String[] args) {
        Tuple2[][] all = {{new Tuple2(0L, 100), new Tuple2(1, 50), new Tuple2(2, 70), new Tuple2(3, 90)},
                          {new Tuple2(4, 150), new Tuple2(5, 20), new Tuple2(6, 10), new Tuple2(7, 60)}};
        Map<Integer, Set<Long>> greedy = greedPartition(all, 550/2);
        for (int key : greedy.keySet()) {
            System.out.println(key + ":");
            System.out.println(Arrays.toString(greedy.get(key).toArray()));
        }
    }
}
