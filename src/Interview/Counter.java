package Interview;

/**
 * Created by yongyangyu on 11/24/14.
 */
public class Counter {
    private static int cnt;
    private static long startTime;
    private static int interval;

    public Counter() {
        cnt = 0;
        startTime = System.currentTimeMillis();
        interval = 5;
    }
    public String call() {
        long now = System.currentTimeMillis();
        if ((now - startTime) / 1000 < interval) {
            cnt ++;
            return new Integer(cnt).toString();
        }
        cnt = 0;
        startTime = now;
        return "Counter reset";
    }

    public static void main(String[] args) {
        Counter timer = new Counter();
        while (true) {
            String msg = null;
            msg = timer.call();
            System.out.println(msg);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Catch an Exception");
                e.printStackTrace();
            }
        }
    }
}
