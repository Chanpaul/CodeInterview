package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 5/11/15.
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to f
 * irst take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class TopologicalSortLeetCode {
    public static boolean canSort(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        Map<Integer, Integer> depend = new HashMap<>();
        Set<Integer> candid = new HashSet<>();
        for (int i = 0; i < numCourses; i ++) {
            depend.put(i, 0);
            candid.add(i);
        }

        for (int[] tuple : prerequisites) {
            depend.put(tuple[0], depend.get(tuple[0]) + 1);
            candid.remove(tuple[0]);
            if (!courses.containsKey(tuple[1])) {
                List<Integer> val = new ArrayList<>();
                val.add(tuple[0]);
                courses.put(tuple[1], val);
            }
            else {
                List<Integer> val = courses.get(tuple[1]);
                val.add(tuple[0]);
                courses.put(tuple[1], val);
            }
        }
        // q is the list of courses with no dependency
        List<Integer> q = new ArrayList<>();
        Iterator<Integer> iter = candid.iterator();
        while(iter.hasNext()) {
            int tmp = iter.next();
            depend.remove(tmp);
            q.add(tmp);
        }
        if (q.isEmpty()) {
            return false;
        }
        while (!q.isEmpty()) {
            int curr = q.remove(0);
            List<Integer> free = null;
            if (courses.containsKey(curr)) {
                free = courses.remove(curr);
                for (int x : free) {
                    depend.put(x, depend.get(x) - 1);
                    if (depend.get(x) == 0) {
                        q.add(x);
                    }
                }
            }
        }
        if (!courses.isEmpty()) {
            return false;
        }
        return true;
    }

    public static int[] sortOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        Map<Integer, Integer> depend = new HashMap<>();
        Set<Integer> candid = new HashSet<>();
        List<Integer> order = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            depend.put(i, 0);
            candid.add(i);
        }

        for (int[] tuple : prerequisites) {
            depend.put(tuple[0], depend.get(tuple[0]) + 1);
            candid.remove(tuple[0]);
            if (!courses.containsKey(tuple[1])) {
                List<Integer> val = new ArrayList<>();
                val.add(tuple[0]);
                courses.put(tuple[1], val);
            }
            else {
                List<Integer> val = courses.get(tuple[1]);
                val.add(tuple[0]);
                courses.put(tuple[1], val);
            }
        }
        // q is the list of courses with no dependency
        List<Integer> q = new ArrayList<>();
        Iterator<Integer> iter = candid.iterator();
        while(iter.hasNext()) {
            int tmp = iter.next();
            depend.remove(tmp);
            q.add(tmp);
        }
        if (q.isEmpty()) {
            return new int[0];
        }
        while (!q.isEmpty()) {
            int curr = q.remove(0);
            order.add(curr);
            List<Integer> free = null;
            if (courses.containsKey(curr)) {
                free = courses.remove(curr);
                for (int x : free) {
                    depend.put(x, depend.get(x) - 1);
                    if (depend.get(x) == 0) {
                        q.add(x);
                    }
                }
            }
        }
        if (!courses.isEmpty()) {
            return new int[0];
        }
        int[] res = new int[order.size()];
        for (int i = 0; i < order.size(); i ++) {
            res[i] = order.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int numCourses = 2;//9;
        int[][] prerequisites = {{0, 1}, {1, 0}};//{{6, 0}, {2, 0}, {7, 6}, {8, 7},
                                // {3, 2}, {2, 1}, {4, 1}, {3, 4},
                                // {5, 4}, {5, 3}, {8, 3}};
        System.out.println(canSort(numCourses, prerequisites));
        int[] order = sortOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(order));
    }
}
