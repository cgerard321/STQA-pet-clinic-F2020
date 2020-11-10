package org.springframework.samples.petclinic.model;


import java.util.ArrayList;

import java.util.List;

public class Schedules {

    private List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        if (scheduleList == null)
            scheduleList = new ArrayList<>();

        return scheduleList;
    }

}
