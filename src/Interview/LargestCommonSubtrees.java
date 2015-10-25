package Interview;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by yongyangyu on 1/10/15.
 */
public class LargestCommonSubtrees {
    static class TreeNode {
        int val;
        List<TreeNode> subNodes;
        int hashVal;
        TreeNode(int val, List<TreeNode> subNodes) {
            this.val = val;
            this.subNodes = subNodes;
            hashVal = 0;
        }
    }

    /*
     * @param root -- root of the tree
     * @param map -- mapping from hash value to all the treenode with that value
     */
    public static void postOrder(TreeNode root, Map<Integer, List<TreeNode>> map) {
        if (root.subNodes == null) {
            root.hashVal = 1;
            if (map.containsKey(1)) {
                List<TreeNode> value = map.get(1);
                value.add(root);
                map.put(1, value);
            }
            else {
                List<TreeNode> value = new ArrayList<>();
                value.add(root);
                map.put(1, value);
            }
            //System.out.print(root.val + " ");
            return;
        }
        else {
            int hash = 1;
            hash = hash << root.subNodes.size();
            for (TreeNode x : root.subNodes) {
                postOrder(x, map);
            }
            for (TreeNode x : root.subNodes) {
                hash += x.hashVal;
            }
            root.hashVal = hash;
            if (map.containsKey(hash)) {
                List<TreeNode> value = map.get(hash);
                value.add(root);
                map.put(hash, value);
            }
            else {
                List<TreeNode> value = new ArrayList<>();
                value.add(root);
                map.put(hash, value);
            }
            //System.out.print(root.val + " ");
            return;
        }
    }

    public static void main(String[] args) {
        /*
         *             1
         *
         *         /  \    \
         *        2    3    4
         *       / \       / \
         *      5   6     8   9
         *           \         \
         *            7         10
         * */
        TreeNode r1 = new TreeNode(1, new ArrayList<>());
        TreeNode r2 = new TreeNode(2, new ArrayList<>());
        TreeNode r3 = new TreeNode(3, null);
        TreeNode r4 = new TreeNode(4, new ArrayList<>());
        TreeNode r5 = new TreeNode(5, null);
        TreeNode r6 = new TreeNode(6, new ArrayList<>());
        TreeNode r7 = new TreeNode(7, null);
        TreeNode r8 = new TreeNode(8, null);
        TreeNode r9 = new TreeNode(9, new ArrayList<>());
        TreeNode r10 = new TreeNode(10, null);

        r1.subNodes.add(r2);
        r1.subNodes.add(r3);
        r1.subNodes.add(r4);

        r2.subNodes.add(r5);
        r2.subNodes.add(r6);

        r6.subNodes.add(r7);

        r4.subNodes.add(r8);
        r4.subNodes.add(r9);

        r9.subNodes.add(r10);

        Map<Integer, List<TreeNode>> map = new HashMap<>();

        postOrder(r1, map);
        Set<Integer> keys = map.keySet();
        int maxKey = Integer.MIN_VALUE;
        List<TreeNode> candid = null;
        for (Integer key : keys) {
            if (maxKey < key && map.get(key).size() > 1) {
                maxKey = key;
                candid = map.get(key);
            }
        }
        for (TreeNode x : candid) {
            System.out.print(x.val + " ");
        }
    }
}
