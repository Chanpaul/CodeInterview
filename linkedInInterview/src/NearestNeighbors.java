/**
 * Created by yongyangyu on 12/9/14.
 */
import java.util.*;

public class NearestNeighbors {
    List<Point> container;
    PriorityQueue<Point> pq;

    public NearestNeighbors() {
        container = new LinkedList<Point>();
    }

    public void addPoint(Point point) {
        container.add(point);
    }

    public Collection<Point> findNearest(Point focus, int n) {
        pq = new PriorityQueue<Point>(10, new pointComparator(focus));
        for (Point pt : container) {
            pq.add(pt);
        }
        Set<Point> res = new HashSet<Point>();
        for (int i = 0; i < n; i ++) {
            res.add(pq.poll());
        }
        return res;
    }

    public static void main(String [] args) {
        NearestNeighbors nn = new NearestNeighbors();
        nn.addPoint(new Point(0, 1));
        nn.addPoint(new Point(0, 2));
        nn.addPoint(new Point(0, 3));
        nn.addPoint(new Point(0, 4));
        nn.addPoint(new Point(0, 5));
        Collection<Point> candid = nn.findNearest(new Point(0, 0), 3);
        for (Point pt : candid) {
            System.out.println(pt);
        }
    }
}
