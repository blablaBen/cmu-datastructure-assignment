package cmu.data.assignment.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyPool {
    public List<TimeSlot> allTimeSlot;
    public int[] lifeguardTimeOccupying;


    public CompanyPool( List<TimeSlot> allTimeSlot, int totalLifeguard) {
        this.allTimeSlot = allTimeSlot;
        this.lifeguardTimeOccupying = new int[totalLifeguard];
    }



}
