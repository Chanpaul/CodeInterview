import java.util.Comparator;

/**
 * Created by yongyangyu on 12/9/14.
 */
public class pointComparator implements Comparator<Point> {
    Point focus;
    public pointComparator(Point focus) {
        this.focus = focus;
    }

    @Override
    public int compare(Point p1, Point p2) {
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
    }
}
