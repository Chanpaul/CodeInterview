package Interview.Scheduler.DelayedScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yongyangyu on 11/13/15.
 */
public class ScheduledExectorTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Task3 running...");
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);

    }
}
