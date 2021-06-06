/* https://github.com/Baeldung/spring-security-registration/blob/master/src/main/java/com/baeldung/validation/PasswordMatchesValidator.java*/

package com.fullstack.FoodBase.validation;

import com.fullstack.FoodBase.model.Register;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final Register register = (Register) obj;
        return register.getPassword().equals(register.getPasswordConfirm());
    }
}
