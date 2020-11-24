package org.springframework.samples.petclinic.annotation;

import org.springframework.samples.petclinic.validator.OwnerAddressValidator;
import org.springframework.samples.petclinic.validator.OwnerCityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OwnerAddressValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAddress {
    String message() default "Address is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
