package Interview;

/**
 * Created by yongyangyu on 10/11/15.
 * Clone an undirected graph. Each node in the graph contains a
 * label and a list of its neighbors.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<Integer, UndirectedGraphNode> id2Node = new HashMap<>();
        List<UndirectedGraphNode> q = new ArrayList<>();
        q.add(node);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        id2Node.put(clone.label, clone);
        while (!q.isEmpty()) {
            UndirectedGraphNode curr = q.remove(0);
            for (UndirectedGraphNode neighbor: curr.neighbors) {
                if (!id2Node.containsKey(neighbor.label)) {
                    UndirectedGraphNode neighborClone = new UndirectedGraphNode(neighbor.label);
                    id2Node.get(curr.label).neighbors.add(neighborClone);
                    id2Node.put(neighbor.label, neighborClone);
                    q.add(neighbor);
                }
                else {
                    id2Node.get(curr.label).neighbors.add(id2Node.get(neighbor.label));
                }
            }
        }
        return clone;
    }
}
