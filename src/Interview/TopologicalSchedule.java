package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/30/15.
 * Given job dependency graph, such as
 * j1
 * j2 -> j1
 * j3 -> j2, j1
 * j4
 * j5 -> j4
 * Compute the scheduling order of the jobs.
 */
public class TopologicalSchedule {
    static class GraphNode {
        private final int jobId;
        private final Set<Integer> dep;

        GraphNode(int jid, Set<Integer> dep) {
            this.jobId = jid;
            this.dep = dep;
        }
    }

    public List<Integer> schedule(List<GraphNode> jobs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (GraphNode gnode: jobs) {
            map.put(gnode.jobId, new HashSet<>(gnode.dep));
        }
        List<Integer> res = new ArrayList<>();
        while (!map.isEmpty()) {
            List<Integer> noDep = new ArrayList<>();
            for (int id: map.keySet()) {
                if (map.get(id).isEmpty()) {
                    noDep.add(id);
                }
            }
            res.addAll(noDep);
            for (int id: noDep) {
                map.remove(id);
            }
            if (map.isEmpty()) break;
            for (int jid: map.keySet()) {
                Set<Integer> dep = map.get(jid);
                dep.removeAll(noDep);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<GraphNode> graph = new ArrayList<>();
        graph.add(new GraphNode(1, new HashSet<>()));
        Set<Integer> s = new HashSet<>();
        s.add(1);
        graph.add(new GraphNode(2, s));
        s = new HashSet<>();
        s.add(2); s.add(1);
        graph.add(new GraphNode(3, s));
        graph.add(new GraphNode(4, new HashSet<>()));
        s = new HashSet<>();
        s.add(4);
        graph.add(new GraphNode(5, s));
        TopologicalSchedule ts = new TopologicalSchedule();
        System.out.println(Arrays.toString(ts.schedule(graph).toArray()));
    }
}
