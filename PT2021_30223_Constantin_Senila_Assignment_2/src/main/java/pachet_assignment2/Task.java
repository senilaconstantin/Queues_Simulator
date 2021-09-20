package pachet_assignment2;

public class Task implements Comparable<Task> {
    private final int arrivalTime;
    private int processingTime;
    private int id;

    public Task(int id, int arrivalTime, int processingTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "(" + getId() +
                "," + getArrivalTime() +
                "," + getProcessingTime() +
                ")";
    }


    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.arrivalTime, o.arrivalTime);
    }
}

