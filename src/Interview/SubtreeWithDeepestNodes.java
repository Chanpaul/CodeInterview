package Interview;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongyangyu on 1/29/16.
 * An interview question from Facebook reported on www.1point3acres.com.
 * Given a tree, find the smallest subtree that contains all of the tree's deepest nodes.
 *                   a
 *                /  |  \
 *               b   c   d
 *             /   \     |
 *            e     f    g
 *           /     /  \
 *          h     i    j
 *
 * depth of tree: 4
 * deepest nodes:[h,i,j]
 * least common ancestor of [h,i,j]: b
 * return: b
 */
public class SubtreeWithDeepestNodes {
    static class Treenode {
        private String label;
        private Set<Treenode> children;
        Treenode(String label, Set<Treenode> children) {
            this.label = label;
            this.children = children;
        }
    }

    public Treenode leastCommonAncestor(Treenode root) {
        int cntEqul = 0;
        int depth = 0;
        Treenode res = null;
        for (Treenode child : root.children) {
            int curr = height(child);
            if (depth == 0) {
                depth = curr;
                res = child;
            }
            else {
                if (curr > depth) {
                    depth = curr;
                    res = child;
                    cntEqul = 0;
                }
                else if (curr == depth) {
                    cntEqul ++;
                }
            }
        }
        if (cntEqul > 0) return root;
        if (res != null) {
            return leastCommonAncestor(res);
        }
        else return root;
    }

    private int height(Treenode root) {
        if (root.children.isEmpty())
            return 1;
        int h = 0;
        for (Treenode child : root.children) {
            int curr = height(child);
            if (h == 0) h = curr;
            h = h < curr ? curr : h;
        }
        return h+1;
    }

    public static void main(String[] args) {
        SubtreeWithDeepestNodes wsdn = new SubtreeWithDeepestNodes();
        Treenode j = new Treenode("j", new HashSet<>());
        Treenode i = new Treenode("i", new HashSet<>());
        Treenode h = new Treenode("h", new HashSet<>());
        Treenode g = new Treenode("g", new HashSet<>());
        Treenode c = new Treenode("c", new HashSet<>());
        Set<Treenode> echild = new HashSet<>();
        echild.add(h);
        Treenode e = new Treenode("e", echild);
        Set<Treenode> fchild = new HashSet<>();
        fchild.add(i); fchild.add(j);
        Treenode f = new Treenode("f", fchild);
        Set<Treenode> bchild = new HashSet<>();
        bchild.add(e); bchild.add(f);
        Treenode b = new Treenode("b", bchild);
        Set<Treenode> dchild = new HashSet<>();
        dchild.add(g);
        Treenode d = new Treenode("d", dchild);
        Set<Treenode> achild = new HashSet<>();
        achild.add(b); achild.add(c); achild.add(d);
        Treenode a = new Treenode("a", achild);
        System.out.println(wsdn.leastCommonAncestor(a).label);

        Treenode n1 = new Treenode("1", new HashSet<>());
        Treenode n3 = new Treenode("3", new HashSet<>());
        Set<Treenode> c2 = new HashSet<>();
        c2.add(n3);
        Treenode n2 = new Treenode("2", c2);
        Set<Treenode> c0 = new HashSet<>();
        c0.add(n1); c0.add(n2);
        Treenode n0 = new Treenode("0", c0);
        System.out.println(wsdn.leastCommonAncestor(n0).label);
    }
}
