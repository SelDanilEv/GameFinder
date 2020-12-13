package com.defender.test.Validator;


import com.defender.test.forms.RegistrationTeacherModel;
import com.defender.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationTeacherModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationTeacherModel user = (RegistrationTeacherModel) o;

        if(userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username is already in use");
        }
    }
}