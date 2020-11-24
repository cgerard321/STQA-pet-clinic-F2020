package org.springframework.samples.petclinic.validator;

import org.springframework.samples.petclinic.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OwnerPhoneValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public void initialize(ValidPhoneNumber ownerPhone) {
    }

    @Override
    public boolean isValid(String ownerField, ConstraintValidatorContext context) {

        //if the owner field is null, return true because the @NotEmpty annotation will be called
        if(ownerField == null)
            return true;

        //Regex to look if the phone number contains only number and has a length of 10
        String regex = "^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$";
        if(ownerField.matches(regex))
            return true;
        else
            return false;
    }
}
