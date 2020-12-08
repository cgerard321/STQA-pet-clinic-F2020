package org.springframework.samples.petclinic.util;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.Calendar;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

//Added by Ernest not used yet, may be of use later.


public class DateMethods {
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
