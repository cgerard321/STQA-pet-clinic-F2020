package org.springframework.samples.petclinic.model;

import javax.persistence.*;


@Entity(name = "vet_schedule")
@Table(name = "vet_schedule")
public class Schedule {

    @Id
    @Column(name = "vet_id")
    private int vetId;


    @Column(name = "day_available")
    private int dayAvailable;


    public Schedule(int vetId, int dayAvailable) {
        this.vetId = vetId;
        this.dayAvailable = dayAvailable;
    }

    public Schedule() {
    }


    public int getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(int dayAvailable) {

        this.dayAvailable = dayAvailable;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public int getVetId() {
        return vetId;
    }
}
