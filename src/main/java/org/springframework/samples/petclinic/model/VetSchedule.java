package org.springframework.samples.petclinic.model;

import java.time.DayOfWeek;
import java.util.List;


public class VetSchedule {

    protected Integer vet_id;
    protected String room_id;
    protected List<DayOfWeek> day_available;

    public VetSchedule(Integer vet_id, String room_id, List<DayOfWeek> day_available) {
        this.vet_id = vet_id;
        this.room_id = room_id;
        this.day_available = day_available;
    }

    public VetSchedule() {
    }

    public Integer getVet_id() {
        return vet_id;
    }

    public void setVet_id(Integer vet_id) {
        this.vet_id = vet_id;
    }

    public String getRoomId() {
        return room_id;
    }

    public List<DayOfWeek> getDayAvailable() {
        return day_available;
    }

    public void setRoomId(String room_id) {
        this.room_id = room_id;
    }

    public void setDayAvailable(List<DayOfWeek> day_available) {
        this.day_available = day_available;
    }

    public Integer getVetId() {
        return vet_id;
    }

    public void setVetId(Integer vet_id) {
        this.vet_id = vet_id;
    }
}
