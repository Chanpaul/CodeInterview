package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 11/24/14.
 */
public class TestBipartiteGraph {
    public static int testColor(int[][] G, int v, int[] color) { // test color conflict and potential candidate
        int neighborColor = -1;
        for (int j = 0; j < G[v].length; j ++) {
            if (G[v][j] > 0 && color[j] >= 0) {
                if (neighborColor < 0) {
                    neighborColor = color[j];
                }
                else {
                    if (neighborColor != color[j]) {
                        return -1;   // current vertex cannot be colored
                    }
                }
            }
        }
        if (neighborColor < 0) {
            return 0;
        }
        else {
            return 1-neighborColor;
        }
    }
    public static boolean isBipartite(int[][] G) {  // test if graph G is bipartite
        int n = G.length;  // number of rows, and cols
        int[] color = new int[n];
        List<Integer> queue = new LinkedList<Integer>(); // queue for bfs
        for (int i = 0; i < n; i ++) {
            color[i] = -1;
        }
        // start from vertex 0
        for (int v = 0; v < n; v ++) {
            int c = testColor(G, v, color);
            if (c < 0) {
                return false;
            }
            if (color[v] < 0) {
                color[v] = c;
                queue.add(v);
                while (!queue.isEmpty()) {
                    int curr = queue.remove(0);
                    for (int j = 0; j < G[curr].length; j ++) {
                        if (G[curr][j] > 0) {
                            if (color[j] >= 0) {
                                if (color[j] != 1 - color[curr]) {
                                    return false;
                                }
                            } else {
                                color[j] = 1 - color[curr];
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] G1 = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};  // a 3-clique
        int[][] G2 = {{0,0,0,1,1}, {0,0,0,1,0}, {0,0,0,1,1}, {1,1,1,0,0}, {1,0,1,0,0}};
        int[][] G3 = {{0,0,1,0}, {0,0,1,1}, {1,1,0,0}, {0,1,0,0}};
        int[][] G4 = {{0,0,1,0}, {0,0,0,1}, {1,0,0,0}, {0,1,0,0}};
        System.out.println("G1 is bipartite? " + isBipartite(G1));
        System.out.println("G2 is bipartite? " + isBipartite(G2));
        System.out.println("G3 is bipartite? " + isBipartite(G3));
        System.out.println("G4 is bipartite? " + isBipartite(G4));
    }
}
