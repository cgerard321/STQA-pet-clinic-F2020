package org.springframework.samples.petclinic.validator;

import org.springframework.samples.petclinic.annotation.ValidCity;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OwnerCityValidator implements ConstraintValidator<ValidCity, String> {

    @Override
    public void initialize(ValidCity ownerCity) {
    }

    @Override
    public boolean isValid(String ownerField, ConstraintValidatorContext context) {

        //if the owner field is null, return true because the @NotEmpty annotation will be called
        if(ownerField == null)
            return true;

        int len = ownerField.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a letter
            // if it is not a letter ,it will return false
            if ((Character.isLetter(ownerField.charAt(i)) == false)) {
                if(ownerField.charAt(i) != '-')
                    return false;
            }
        }
        return true;
    }
}
