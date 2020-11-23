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

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {
    //test change
    /* Test comment from cgerard */
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;

    @Column(name = "image_url")
    private String imageURL;

    @Column
    private int totalRating;

    @Column
    private int timesRated;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
    private Set<Visit> visits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
    private Set<Rating> ratings;

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public PetType getType() {
        return this.type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return this.owner;
    }

    protected void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getTimesRated() {
        return timesRated;
    }

    public void setTimesRated(int timesRated) {
        this.timesRated = timesRated;
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    protected void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }


    protected Set<Rating> getRatingsInternal() {
        if (this.ratings == null) {
            this.ratings = new HashSet<>();
        }
        return this.ratings;
    }

    protected void setRatingsInternal(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        List<Rating> sortedRatings = new ArrayList<>(getRatingsInternal());
        PropertyComparator.sort(sortedRatings, new MutableSortDefinition("username", false, false));
        return Collections.unmodifiableList(sortedRatings);

    }

    public void addRating(Rating rating) {
        getRatingsInternal().add(rating);
        rating.setPet(this);
    }


    public double getAverageRating(){
        if (totalRating == 0){
            return 0;
        }
        else {
            return (double) totalRating / timesRated;
        }
    }

    public String toJsonString() {
        return "{" +
               "\"name\":\"" + getName() + "\", " +
               "\"birthdate\":\"" + birthDate + "\", " +
               "\"type\":\"" + type + "\", " +
               "\"imageURL\":\"" + imageURL + "\", " +
               "\"totalRating\":\"" + totalRating + "\", " +
               "\"timesRated\":\"" + timesRated +"\"" +
               "}";
    }
}
