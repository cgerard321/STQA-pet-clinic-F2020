package org.springframework.samples.petclinic.annotation;

import org.springframework.samples.petclinic.validator.OwnerCityValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OwnerCityValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCity {
    String message() default "City name is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
