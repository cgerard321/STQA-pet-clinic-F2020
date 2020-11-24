package org.springframework.samples.petclinic.annotation;

import org.springframework.samples.petclinic.validator.OwnerNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OwnerNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Name is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
