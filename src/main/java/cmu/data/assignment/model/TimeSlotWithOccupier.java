package cmu.data.assignment.model;

import java.util.List;

public class TimeSlotWithOccupier {
    public String[] occupyers;
    public int timeLength;
    public int startTime;
    public int toTime;

    public TimeSlotWithOccupier(String[] occupyers, int timeLength, int startTime, int toTime) {
        this.occupyers = occupyers;
        this.timeLength = timeLength;
        this.startTime = startTime;
        this.toTime = toTime;
    }
}
