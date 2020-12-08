package org.springframework.samples.petclinic.model;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule extends NamedEntity {
        private String name;

    public Schedule(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Schedule() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
