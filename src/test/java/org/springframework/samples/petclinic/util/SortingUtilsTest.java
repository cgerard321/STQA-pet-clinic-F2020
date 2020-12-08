package org.springframework.samples.petclinic.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class SortingUtilsTest {

    private Collection<Owner> owners;
    private SortingUtils sortingUtils = new SortingUtils();

    @Autowired
    private ClinicService clinicService;

    @BeforeEach
    void setUp() {
        owners = clinicService.findAllOwner();
    }

    @Test
    void sortByFirstNameTest() {
        List<Owner> expectedSortedList = new ArrayList<>(owners);
        //with the sortingUtils object I call the method to sort the collection of owner
        List<Owner> actualSortedList = sortingUtils.sortByFirstName(owners);

        //This function sort the collection of owner by the field specifed - in this case the first name
        Comparator<Owner> comparator = Comparator.comparing(Person::getFirstName);
        Collections.sort(expectedSortedList, comparator);

        assertEquals(expectedSortedList, actualSortedList);
    }

    @Test
    void sortByLastNameTest() {
        List<Owner> expectedSortedList = new ArrayList<>(owners);
        //with the sortingUtils object I call the method to sort the collection of owner
        List<Owner> actualSortedList = sortingUtils.sortByLastName(owners);

        //This function sort the collection of owner by the field specifed - in this case the last name
        Comparator<Owner> comparator = Comparator.comparing(Person::getLastName);
        Collections.sort(expectedSortedList, comparator);

        assertEquals(expectedSortedList, actualSortedList);
    }

    @Test
    void sortByStateNameTest() {
        List<Owner> expectedSortedList = new ArrayList<>(owners);
        //with the sortingUtils object I call the method to sort the collection of owner
        List<Owner> actualSortedList = sortingUtils.sortByStateName(owners);

        //This function sort the collection of owner by the field specifed - in this case the state name
        Comparator<Owner> comparator = Comparator.comparing(Owner::getState);
        Collections.sort(expectedSortedList, comparator);

        assertEquals(expectedSortedList, actualSortedList);
    }

    @Test
    void sortByCityNameTest() {
        List<Owner> expectedSortedList = new ArrayList<>(owners);
        //with the sortingUtils object I call the method to sort the collection of owner
        List<Owner> actualSortedList = sortingUtils.sortByCityName(owners);

        //This function sort the collection of owner by the field specifed - in this case the city name
        Comparator<Owner> comparator = Comparator.comparing(Owner::getCity);
        Collections.sort(expectedSortedList, comparator);

        assertEquals(expectedSortedList, actualSortedList);
    }
}
