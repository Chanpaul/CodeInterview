package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 4/8/15.
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 *
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 *
 */
public class NumIslands {
    public static class Tuple {
        private int p1;
        private int p2;
        Tuple(int x, int y) {
            p1 = x;
            p2 = y;
        }
        public int getX() {
            return p1;
        }
        public int getY() {
            return p2;
        }
        @Override public int hashCode() {
            return p1 + p2;
        }

        @Override public boolean equals(Object o) {
            if (o instanceof Tuple) {
                if (this == (Tuple)o) {
                    return true;
                }
                else if (p1 == ((Tuple) o).getX() && p2 == ((Tuple) o).getY()) {
                    return true;
                }
                return false;
            }
            else {
                return false;
            }
        }
    }

    public static int nislands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Map<Tuple, Boolean> pos = new HashMap<Tuple, Boolean>();
        int maxx = grid.length;
        int maxy = grid[0].length;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == '1') {
                    pos.put(new Tuple(i, j), true);
                }
            }
        }
        int num = 0;
        Set<Tuple> candid = pos.keySet();
        while (candid.size() != 0) {
            Tuple random = null;
            Iterator<Tuple> iter = candid.iterator();
            if (iter.hasNext()) {
                random = iter.next();
                candid.remove(random);
            }
            List<Tuple> q = new LinkedList<Tuple>();
            q.add(random);
            while (!q.isEmpty()) {
                Tuple head = q.remove(0);
                List<Tuple> neighbors = getNeighors(head.getX(), head.getY(), maxx, maxy);
                for (Tuple neighbor: neighbors) {
                    if (candid.contains(neighbor)) {
                        q.add(neighbor);
                        candid.remove(neighbor);
                    }
                }
            }
            num ++;
        }
        return num;

    }
    private static List<Tuple> getNeighors(int x, int y, int maxx, int maxy) {
        List<Tuple> res = new ArrayList<Tuple>();
        if (x + 1 < maxx) {
            // x+ 1 is valid
            Tuple curr = new Tuple(x+1, y);
            res.add(curr);
        }
        if (x - 1 >= 0) {
            Tuple curr = new Tuple(x-1, y);
            res.add(curr);
        }
        if (y + 1 < maxy) {
            Tuple curr = new Tuple(x, y+1);
            res.add(curr);
        }
        if (y - 1 >= 0) {
            Tuple curr = new Tuple(x, y-1);
            res.add(curr);
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        System.out.println(nislands(grid));
    }

    /*
     * replace Tuple with ArrayList<> runs faster
        public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Map<List<Integer>, Boolean> pos = new HashMap<List<Integer>, Boolean>();
        int maxx = grid.length;
        int maxy = grid[0].length;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == '1') {
                    List<Integer> curr = new ArrayList<Integer>();
                    curr.add(i);
                    curr.add(j);
                    pos.put(curr, true);
                }
            }
        }
        int num = 0;
        Set<List<Integer>> candid = pos.keySet();
        while (candid.size() != 0) {
            List<Integer> random = null;
            Iterator<List<Integer>> iter = candid.iterator();
            if (iter.hasNext()) {
                random = iter.next();
                candid.remove(random);
            }
            List<List<Integer>> q = new LinkedList<List<Integer>>();
            q.add(random);
            while (!q.isEmpty()) {
                List<Integer> head = q.remove(0);
                List<List<Integer>> neighbors = getNeighors(head.get(0), head.get(1), maxx, maxy);
                for (List<Integer> neighbor: neighbors) {
                    if (candid.contains(neighbor)) {
                        q.add(neighbor);
                        candid.remove(neighbor);
                    }
                }
            }
            num ++;
        }
        return num;
    }
    private static List<List<Integer>> getNeighors(int x, int y, int maxx, int maxy) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (x + 1 < maxx) {
            // x+ 1 is valid
            List<Integer> curr = new ArrayList<Integer>();
            curr.add(x+1);
            curr.add(y);
            res.add(curr);
        }
        if (x - 1 >= 0) {
            List<Integer> curr = new ArrayList<Integer>();
            curr.add(x-1);
            curr.add(y);
            res.add(curr);
        }
        if (y + 1 < maxy) {
            List<Integer> curr = new ArrayList<Integer>();
            curr.add(x);
            curr.add(y+1);
            res.add(curr);
        }
        if (y - 1 >= 0) {
            List<Integer> curr = new ArrayList<Integer>();
            curr.add(x);
            curr.add(y - 1);
            res.add(curr);
        }
        return res;
    }

     */
}
