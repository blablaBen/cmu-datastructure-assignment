package cmu.data.assignment.model;

public class Lifeguard {
    private int startTime;
    private int endTime;

    public Lifeguard( String startTime, String endTime) {
        this.startTime = Integer.parseInt(startTime);
        this.endTime = Integer.parseInt(endTime);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
