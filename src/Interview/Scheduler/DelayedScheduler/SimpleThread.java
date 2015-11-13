package Interview.Scheduler.DelayedScheduler;

/**
 * Created by yongyangyu on 11/13/15.
 */
public class SimpleThread {
    public static void main(String[] args) {
        final long interval = 1000;
        Runnable runnable = () -> {
            while (true) {
                System.out.println("Tasking running...");
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}
