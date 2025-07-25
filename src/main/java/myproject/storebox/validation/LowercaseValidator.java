package myproject.storebox.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<Lowercase, String> {
    
    @Override
    public void initialize(Lowercase constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null values
        }
        return value.equals(value.toLowerCase());
    }
}
