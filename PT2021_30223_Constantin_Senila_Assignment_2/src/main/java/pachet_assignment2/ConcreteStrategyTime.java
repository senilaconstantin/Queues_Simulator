package pachet_assignment2;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
        int min = 99999;
        int id = 0;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() < min) {
                min = s.getWaitingPeriod().get();
                id = s.getId();
            }
        }
        Server s = servers.get(id);
        s.setModQ(true);
        s.addTask(t);
        servers.set(id,s);
    }
}
