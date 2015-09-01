package Interview;

/**
 * Created by yongyangyu on 9/1/15.
 */
import java.util.*;
import java.util.Comparator;

public class NearestPoints {
    private final Point focus;
    private final List<Point> container;
    private PriorityQueue<Point> pq;

    public NearestPoints(Point focus) {
        this.focus = focus;
        container = new LinkedList<>();
    }

    public void addPoint(Point point) {
        container.add(point);
    }

    public Collection<Point> findNearest(int n) {
        Comparator<Point> cmp = (Point p1, Point p2) -> {
            if (Math.pow(focus.x - p1.x, 2) + Math.pow(focus.y - p1.y, 2) >
                    Math.pow(focus.x - p2.x, 2) + Math.pow(focus.y - p2.y, 2)) {
                return 1;
            }
            else if (Math.pow(focus.x - p1.x, 2) + Math.pow(focus.y - p1.y, 2) <
                    Math.pow(focus.x - p2.x, 2) + Math.pow(focus.y - p2.y, 2)) {
                return -1;
            }
            else {
                return 0;
            }
        };
        pq = new PriorityQueue<>(10, cmp);
        for (Point pt : container) {
            pq.add(pt);
        }
        Set<Point> res = new HashSet<>();
        for (int i = 0; i < n; i ++) {
            res.add(pq.poll());
        }
        return res;
    }

    public static void main(String [] args) {
        NearestPoints nn = new NearestPoints(new Point(0, 0));
        nn.addPoint(new Point(0, 1));
        nn.addPoint(new Point(0, 2));
        nn.addPoint(new Point(0, 3));
        nn.addPoint(new Point(0, 4));
        nn.addPoint(new Point(0, 5));
        Collection<Point> candid = nn.findNearest(3);
        for (Point pt : candid) {
            System.out.println(pt);
        }
    }
}