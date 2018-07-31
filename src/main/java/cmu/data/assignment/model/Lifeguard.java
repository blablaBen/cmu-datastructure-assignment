package cmu.data.assignment.model;

public class Lifeguard {
    private int startTime;
    private int toTime;

    public Lifeguard( String startTime, String toTime) {
        this.startTime = Integer.parseInt(startTime);
        this.toTime = Integer.parseInt(toTime);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getToTime() {
        return toTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public int getRealEndTime() {
        return this.toTime - 1;
    }

}
