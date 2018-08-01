package cmu.data.assignment.model;

public class Lifeguard {


    public int name;
    public int startTime;
    public int toTime;

    public Lifeguard( int id, String startTime, String toTime) {
        this.name = id;
        this.startTime = Integer.parseInt(startTime);
        this.toTime = Integer.parseInt(toTime);
    }

}
