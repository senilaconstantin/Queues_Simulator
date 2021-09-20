package pachet_assignment2;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private final ArrayList<Thread> thread;
    private int sum;

    public Scheduler(int maxNoServers) {
        this.servers = new ArrayList<>();
        this.thread = new ArrayList<>();
        this.sum = 0;
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server(i + 1));
            thread.add(new Thread(servers.get(i)));
            thread.get(i).start();
        }
    }

    public int getSum() {
        return sum;
    }

    public void dispatchTask(Task t) {
        int nrMinQ = nrMinQueue();
        Server s = servers.get(nrMinQ);
        ///--------
        this.sum+=this.servers.get(nrMinQ).getWaitingPeriod().get();
        ///----------
        s.addTask(t);
        servers.set(nrMinQ, s);
        if (!this.servers.get(nrMinQ).isModQ()) {
            Server e = servers.get(nrMinQ);
            e.setModQ(true);
            servers.set(nrMinQ, e);
            thread.set(nrMinQ, new Thread(servers.get(nrMinQ)));
            thread.get(nrMinQ).start();
        }

    }

    public int nrMinQueue() {
        int waitTime = 99999999;
        int nrMinQue = 0;
        for (int i = 0; i < servers.size(); i++) {
            if (this.servers.get(i).getWaitingPeriod().get() < waitTime) {
                waitTime = this.servers.get(i).getWaitingPeriod().get();
                nrMinQue = i;
            }
            if (waitTime == 0) {
                break;
            }
        }
        return nrMinQue;
    }


    public void deadThread() {
        for (int i = 0; i < this.servers.size(); i++) {
            Server s = this.servers.get(i);
            s.setModQ(false);
            this.servers.set(i, s);
        }
    }

    public int getTotWait() {
        int max = 0;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() > max) {
                max = s.getWaitingPeriod().get();
            }
        }

        return max;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Server serv : servers) {
            s.append("Queue ").append(serv.getId()).append(": ").append(serv.toString()).append("\n");
        }

        return s.toString();
    }


}
