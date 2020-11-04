package org.springframework.samples.petclinic.model;

import java.time.DayOfWeek;

public class VetSchedule {

    protected Integer vet_id;
    protected String room_id;
    protected DayOfWeek day_available;

    public VetSchedule(String room_id, DayOfWeek day_available) {
        this.room_id = room_id;
        this.day_available = day_available;
    }

    public String getRoomId() {
        return room_id;
    }

    public DayOfWeek getDayAvailable() {
        return day_available;
    }

    public void setRoomId(String room_id) {
        this.room_id = room_id;
    }

    public void setDayAvailable(DayOfWeek day_available) {
        this.day_available = day_available;
    }

    public Integer getVetId() {
        return vet_id;
    }

    public void setVetId(Integer vet_id) {
        this.vet_id = vet_id;
    }
}
