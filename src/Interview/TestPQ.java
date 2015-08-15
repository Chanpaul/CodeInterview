package Interview;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Created by yongyangyu on 11/18/14.
 */
public class TestPQ {
    public static void main(String [] args) {
        Comparator<String> myComparator = new StringLenComparator();
        PriorityQueue<String> pq = new PriorityQueue<String>(10, myComparator);
        pq.add("short");
        pq.add("very very long");
        pq.add("medium");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
