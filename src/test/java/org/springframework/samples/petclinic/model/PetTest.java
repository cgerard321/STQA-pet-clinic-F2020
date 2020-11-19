package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetTest {

    @Test
    void testGetAverageRating(){
        Pet pet1 = new Pet();
        pet1.setTotalRating(15);
        pet1.setTimesRated(2);

        Pet pet2 = new Pet();
        pet2.setTotalRating(0);
        pet2.setTimesRated(0);

        double actualResult = pet1.getAverageRating();
        assertEquals(7.5, actualResult);

        actualResult = pet2.getAverageRating();
        assertEquals(0, actualResult);
    }
}
