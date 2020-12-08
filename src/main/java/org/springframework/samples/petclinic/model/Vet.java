/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_schedule", joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "day_id"))
    private List<Schedule> schedules;


    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    protected List<Schedule> getScheduleInternal() {
        if (this.schedules == null) {
            this.schedules = new ArrayList<>();
        }
        return this.schedules;
    }


    protected void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void setScheduleInternal(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    @XmlElement
    public List<Schedule> getSchedules() {
        List<Schedule> sortedSpecs = new ArrayList<>(getScheduleInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("id", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    /*Code by Alexandra Mormontoy*/
    @XmlElement
    public List<Schedule> getSchedulesLastMinute() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        List<Schedule> holder = new ArrayList<>(getScheduleInternal());
        List<Schedule> sortedSpecs = new ArrayList<>();

        for(Schedule sched: holder)
        {
            if(sched.getId() >= dayOfWeek-1 && sched.getId() <= dayOfWeek+1)
            {
                sortedSpecs.add(sched);
            }
        }

        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public int getNrOfDaysAvailable() {
        return getScheduleInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    public void addDayToSchedule(Schedule schedule) {
        getScheduleInternal().add(schedule);
    }

    public void removeDayFromSchedule(Schedule schedule){getScheduleInternal().remove(schedule);}

}
