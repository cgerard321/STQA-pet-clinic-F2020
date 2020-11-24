package org.springframework.samples.petclinic.annotation;

import org.springframework.samples.petclinic.validator.OwnerStateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OwnerStateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidState {
    String message() default "State code is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
