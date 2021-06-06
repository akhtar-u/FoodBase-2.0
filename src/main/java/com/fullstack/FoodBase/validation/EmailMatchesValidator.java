package com.fullstack.FoodBase.validation;

import com.fullstack.FoodBase.model.Register;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailMatchesValidator implements ConstraintValidator<EmailMatches, Object> {

    @Override
    public void initialize(final EmailMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final Register register = (Register) obj;
        return register.getEmail().equals(register.getEmailConfirm());
    }
}
