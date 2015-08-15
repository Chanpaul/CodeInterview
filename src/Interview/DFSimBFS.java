package Interview;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yongyangyu on 3/18/15.
 *
 * Interview question from Medallia onsite.
 *
 * Find a target value in a binary tree in shortest path.
 * Assumption DFS has the potential problem of infinite stack calls. How to
 * limit the memory usage while searching the target in BFS fashion?
 */


public class DFSimBFS implements MyNode {
    private int val;

    public DFSimBFS(int val) {
        this.val = val;
    }

    public int value() {
        return val;
    }

    public MyNode left() {
        return new DFSimBFS(val - 1);
    }

    public MyNode right() {
        return new DFSimBFS(val + 2);
    }

    public static List<Integer> path(MyNode root, int target) {
        int limit = 1;
        List<Integer> res = search(1, limit, target, root);
        while (res == null) {
            limit ++;
            res = search(1, limit, target, root);
        }
        Collections.reverse(res);
        return res;
    }

    private static List<Integer> search(int level, int limit, int target, MyNode root) {
        if (root == null) {
            return null;
        }
        if (root.value() == target) {
            List<Integer> rval = new ArrayList<Integer>();
            rval.add(target);
            return rval;
        }
        if (level < limit) {
            List<Integer> rval = search(level + 1 , limit, target, root.left());
            if (rval != null) {
                rval.add(root.value());
                return rval;
            }
            rval = search(level + 1, limit, target, root.right());
            if (rval != null) {
                rval.add(root.value());
                return rval;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DFSimBFS root = new DFSimBFS(1);
        int target = -7;
        List<Integer> res = path(root, target);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
