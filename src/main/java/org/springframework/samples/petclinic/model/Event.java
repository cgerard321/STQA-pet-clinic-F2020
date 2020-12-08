package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

// Events for the calendar on the welcome page
// Louis Choiniere
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    @NotEmpty
    private int id;

    @Column(name = "dateOfEvent")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate localDate;

    @Column(name = "description")
    private String description;

    @Column(name = "timeOfDay")
    private String time;

//    public Event(String description, String time) {
//        this.description = description;
//        this.time = time;
//    }
//
//    public Event(String description) {
//        this.description = description;
//        this.time = "";
//    }

    public Event() {
        this.localDate = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String toJsonString() {
        return "{" +
            "\"date\":\"" + localDate + "\", " +
            "\"description\":\"" + description + "\", " +
            "\"time\":\"" + time + "\"" +
            "}";
    }
}
