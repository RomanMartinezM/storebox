package myproject.storebox.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LowercaseValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lowercase {
    String message() default "must be lowercase";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
