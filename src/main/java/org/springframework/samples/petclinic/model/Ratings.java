package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

public class Ratings {
    private List<Rating> ratingList;

    public List<Rating> getRatingList() {
        if (ratingList == null)
            ratingList = new ArrayList<>();

        return ratingList;
    }
}
