package org.springframework.samples.petclinic.model;

import java.time.DayOfWeek;
import java.util.List;

public class VetSchedule {

    protected Integer vetId;
    protected String roomId;
    protected List<DayOfWeek> dayAvailable;

    public VetSchedule(Integer vetId, String roomId, List<DayOfWeek> dayAvailable) {
        this.vetId = vetId;
        this.roomId = roomId;
        this.dayAvailable = dayAvailable;
    }

    public VetSchedule() {
    }


    public Integer getVetId() {
        return vetId;
    }

    public void setVetId(Integer vetId) {
        this.vetId = vetId;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<DayOfWeek> getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(List<DayOfWeek> dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

}
