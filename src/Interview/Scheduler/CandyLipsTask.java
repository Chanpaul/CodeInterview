package Interview.Scheduler;

import java.util.Date;

/**
 * Created by yongyangyu on 11/12/15.
 */
public class CandyLipsTask implements Task {
    private final int taskID;
    private Date startDate = null;

    public CandyLipsTask(final int taskID, final Date startDate) {
        this.taskID = taskID;
        this.startDate = startDate;
    }

    @Override
    public void execute() {
        System.out.format("Task %3d: ", taskID);
        System.out.format("%5d seconds passed after scheduling started.%n",
                (System.currentTimeMillis() - startDate.getTime())/1000);
    }
}
