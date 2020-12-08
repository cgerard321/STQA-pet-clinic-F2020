package org.springframework.samples.petclinic.util;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Person;
import java.util.*;

public class SortingUtils {

    public SortingUtils() {
    }

    //method to compare the first name of each owner and then sort the collection
    public List<Owner> sortByFirstName(Collection<Owner> owners) {
        List<Owner> ownerList = new ArrayList<>(owners);
        Comparator<Owner> comparator = Comparator.comparing(Person::getFirstName);
        Collections.sort(ownerList, comparator);

        return ownerList;
    }

    //method to compare the last name of each owner and then sort the collection
    public List<Owner> sortByLastName(Collection<Owner> owners) {
        List<Owner> ownerList = new ArrayList<>(owners);
        Comparator<Owner> comparator = Comparator.comparing(Person::getLastName);
        Collections.sort(ownerList, comparator);

        return ownerList;
    }

    //method to compare the state name of each owner and then sort the collection
    public List<Owner> sortByStateName(Collection<Owner> owners) {
        List<Owner> ownerList = new ArrayList<>(owners);
        Comparator<Owner> comparator = Comparator.comparing(Owner::getState);
        Collections.sort(ownerList, comparator);

        return ownerList;
    }

    //method to compare the city name of each owner and then sort the collection
    public List<Owner> sortByCityName(Collection<Owner> owners) {
        List<Owner> ownerList = new ArrayList<>(owners);
        Comparator<Owner> comparator = Comparator.comparing(Owner::getCity);
        Collections.sort(ownerList, comparator);

        return ownerList;
    }

}
