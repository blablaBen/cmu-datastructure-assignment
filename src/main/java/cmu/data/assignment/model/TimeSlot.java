package cmu.data.assignment.model;

public class TimeSlot {
    public int pointTime;
    public int lifeguardName;
    public boolean isStartTime;

    public TimeSlot(int pointTime, int lifeguardName, boolean isStartTime) {
        this.pointTime = pointTime;
        this.lifeguardName = lifeguardName;
        this.isStartTime = isStartTime;
    }
}
