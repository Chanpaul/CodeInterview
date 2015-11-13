package Interview.Scheduler.DelayedScheduler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yongyangyu on 11/13/15.
 */
public class SimpleTimer {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask running...");
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long interval = 1 * 1000;
        timer.schedule(task, delay, interval);
    }
}
