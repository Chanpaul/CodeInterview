package Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 11/12/15.
 * Given a list of child->parent relationships, build a binary tree out of it.
 * All the element Ids inside the tree are unique.
 *
 * Example:
 *
 * Given the following relationships:
 *
 * Child Parent IsLeft
 * 15 20 true
 * 19 80 true
 * 17 20 false
 * 16 80 false
 * 80 50 false
 * 50 null false
 * 20 50 true
 *
 * You should return the following tree:
 *        50
 *       /  \
 *      20  80
 *     / \  / \
 *    15 17 19 16
 */
public class BinaryTreeChildParentRecover {
    public static class Relation {
        public Integer _parent;
        public Integer _child;
        public boolean _isLeft;
        public Relation(Integer child, Integer parent, boolean isLeft) {
            this._parent = parent;
            this._child = child;
            this._isLeft = isLeft;
        }
    }

    public TreeNode buildTree(List<Relation> data) {
        TreeNode root = null;
        Map<Integer, TreeNode> id2Node = new HashMap<>();
        for (Relation tuple: data) {
            if (!id2Node.containsKey(tuple._child)) {
                id2Node.put(tuple._child, new TreeNode(tuple._child));
            }
            if (tuple._parent == null) {
                root = id2Node.get(tuple._child);
                continue;
            }
            else {
                if (!id2Node.containsKey(tuple._parent)) {
                    id2Node.put(tuple._parent, new TreeNode(tuple._parent));
                }
            }
            if (tuple._isLeft) {
                id2Node.get(tuple._parent).left = id2Node.get(tuple._child);
            }
            else {
                id2Node.get(tuple._parent).right = id2Node.get(tuple._child);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        List<Relation> data = new ArrayList<>();
        data.add(new Relation(15, 20 ,true));
        data.add(new Relation(19, 80, true));
        data.add(new Relation(17, 20, false));
        data.add(new Relation(16, 80, false));
        data.add(new Relation(80, 50, false));
        data.add(new Relation(50, null, false));
        data.add(new Relation(20, 50, true));
        TreeNode root = new BinaryTreeChildParentRecover().buildTree(data);
        root.printTree();
    }
}
