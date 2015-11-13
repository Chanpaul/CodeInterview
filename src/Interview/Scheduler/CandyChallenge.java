package Interview.Scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yongyangyu on 11/12/15.
 */
public class CandyChallenge {
    private Timer timer = new Timer(false);

    private int currentTask;

    private Date startDate;

    private int MAX;

    public CandyChallenge(final Date startDate) {
        currentTask = 1;
        this.startDate = startDate;
    }

    private void scheduleTask(final Date taskStartDate, long interval,
                              final long steadyState, final Task[] tasks) {
        timer.schedule(new CandyLipsTimer(currentTask, tasks[currentTask-1]),
                taskStartDate);
        currentTask ++;
        if (interval <= steadyState) {
            scheduleTask(new Date(taskStartDate.getTime() + interval * 1000),
                    interval << 1, steadyState, tasks);
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private class CandyLipsTimer extends TimerTask {
        private Task task;
        private final int taskID;
        public CandyLipsTimer(final int taskID, final Task task) {
            this.task = task;
            this.taskID = taskID;
        }

        @Override
        public void run() {
            if (taskID == MAX) {
                timer.cancel();
            }
            task.execute();
        }
    }

    public void scheduleTask(final long steadyState, final Task[] tasks)
            throws Exception
    {
        MAX = CandyChallenge.getMAX(steadyState);
        if(tasks.length < MAX)
            throw new Exception("The tasks are too few");
        scheduleTask(startDate, 1, steadyState, tasks);
    }

    public static int getMAX(final long steadyState)
    {
        return (int)(Math.log(steadyState) / Math.log(2)) + 2;
    }

    public static void usage()
    {
        System.err.println("\nUsage: java CandyLipsChallenge steadyState" +
                " startDelay");
        System.err.println(" steadyState The interval between the final two" +
                " tasks\n would be 2^x, where x is the " +
                "greatest\n integral power of 2 that" +
                " is <= steadyState.");
        System.err.println(" startDelay The delay between application" +
                " startup and\n the first task " +
                "execution.");
        System.err.println(" Both arguments are in seconds and must be " +
                "nonnegative.\n");
    }

    public static void main(String[] args) throws Exception
    {
        // Enfore the number of arguments passed to the application.
        if(args.length != 2)
        {
            usage();
            System.exit(1);
        }

        // The delay(in seconds) between the application startup and
        // the first task execution.
        int startDelay = 0;

    /*
    * The interval between the final two tasks would be 2^x, where x is the
    * greatest integral power of 2 that is <= steadyState.
    * steadyState is in seconds.
    */
        int steadyState = 1;

        // Make sure arguments are non-negative integers.
        try
        {
            startDelay = Integer.parseInt(args[1]) * 1000;
            steadyState = Integer.parseInt(args[0]);

            if(startDelay < 0 || steadyState < 0)
                throw new NumberFormatException();

        }
        catch(NumberFormatException ne)
        {
            usage();
            System.exit(1);
        }

        // The start date of the first task.
        final java.util.Date startDate = new java.util.Date((
                new java.util.Date(System.currentTimeMillis()).getTime() +
                        startDelay));

        Task[] tasks = new Task[CandyChallenge.getMAX(steadyState)];
        for(int k = 0; k < tasks.length; )
            tasks[k] = new CandyLipsTask(++k, startDate);

        // Only run application if the steady state is greater than 0.
        if(steadyState > 0)
            new CandyChallenge(startDate).scheduleTask(steadyState, tasks);
    }
}
