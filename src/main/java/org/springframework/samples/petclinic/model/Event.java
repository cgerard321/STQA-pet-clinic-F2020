package org.springframework.samples.petclinic.model;

public class Event {

    private String description;
    private String time;

    public Event(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public Event(String description) {
        this.description = description;
        this.time = "";
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}
