package pachet_assignment2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private final int id;
    private boolean modQ;


    public Server(int id) {
        this.id = id;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public boolean isModQ() {
        return modQ;
    }

    public void setModQ(boolean modQ) {
        this.modQ = modQ;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getId() {
        return id;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        int waitPer = this.waitingPeriod.get();
        waitPer += t.getProcessingTime();
        this.waitingPeriod.set(waitPer);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.getWaitingPeriod().get() == 0 || tasks.peek() == null || tasks.peek().getProcessingTime() == 0)//
        {
            result = new StringBuilder("closed");
        } else {
            int i = 0;
            for (Task t : tasks) {
                if (i == 0) {
                    i = 1;
                    result.append(t.toString());
                } else
                    result.append("; ").append(t.toString());
            }
        }
        return result.toString();
    }


    @Override
    public void run() {
        while (this.modQ) {
            while (this.tasks.peek() != null) {
                int pt = tasks.peek().getProcessingTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int nrW = this.waitingPeriod.get();
                nrW--;
                this.waitingPeriod.set(nrW);
                pt--;
                assert tasks.peek() != null;
                tasks.peek().setProcessingTime(pt);
                if (pt == 0) {
                    assert tasks.peek() != null;
                    tasks.peek().setProcessingTime(0);
                    tasks.poll();
                }
            }
        }
    }
}
