package org.springframework.samples.petclinic.validator;

import org.springframework.samples.petclinic.annotation.ValidAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OwnerAddressValidator implements ConstraintValidator<ValidAddress, String> {

    @Override
    public void initialize(ValidAddress ownerAddress) {
   }

   @Override
   public boolean isValid(String ownerField, ConstraintValidatorContext context) {

        //if the owner field is null, return true because the @NotEmpty annotation will be called
        if(ownerField == null)
           return true;

       String regex = "^(\\d+) ?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\\d*)?$";
       if(ownerField.matches(regex))
           return true;
       else
           return false;
   }
}
