package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Entity
@Table(name = "reminders")
public class Reminder extends BaseEntity {

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;


    @NotEmpty
    @Column(name = "event_description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;


    public Reminder() {
        this.date = LocalDate.now();
    }


    public LocalDate getEventDate() {
        return this.date;
    }

    public void setEventDate(LocalDate date) {
        this.date = date;
    }

    public String getEventDescription() {
        return this.description;
    }

    public void setEventDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

}

