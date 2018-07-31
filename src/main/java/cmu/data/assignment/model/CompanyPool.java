package cmu.data.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class CompanyPool {

    List<Lifeguard> currentLifeguard;

    public CompanyPool(List<Lifeguard> currentLifeguard) {
        this.currentLifeguard = currentLifeguard;
    }

    public List<Lifeguard> getCurrentLifeguard() {
        return currentLifeguard;
    }

    public void setCurrentLifeguard(List<Lifeguard> currentLifeguard) {
        this.currentLifeguard = currentLifeguard;
    }

    public List<Lifeguard> getFiredLifeguard(int getFiredIndex) {
        List<Lifeguard> result = new ArrayList<Lifeguard>();
        for(int i = 0; i<this.currentLifeguard.size(); i++) {
            if(i != getFiredIndex) {
                result.add(this.currentLifeguard.get(i));
            }
        }
        return result;
    }


}
