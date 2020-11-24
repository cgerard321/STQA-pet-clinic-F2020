package org.springframework.samples.petclinic.validator;

import org.springframework.samples.petclinic.annotation.ValidName;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OwnerNameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName ownerName) {
    }

    @Override
    public boolean isValid(String ownerField, ConstraintValidatorContext context) {

        //if the owner field is null, return true because the @NotEmpty annotation will be called
        if(ownerField == null)
            return true;

        //get the lenght of the the value
        int len = ownerField.length();

        //for loops to look up every char of the string value
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
