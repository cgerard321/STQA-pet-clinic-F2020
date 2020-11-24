package org.springframework.samples.petclinic.annotation;



import org.springframework.samples.petclinic.validator.OwnerPhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OwnerPhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Phone Number is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
