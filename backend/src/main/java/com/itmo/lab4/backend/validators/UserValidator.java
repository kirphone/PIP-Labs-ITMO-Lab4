package com.itmo.lab4.backend.validators;

import com.itmo.lab4.backend.database.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Service
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User)obj;
        Pattern loginPattern = Pattern.compile("^[a-z][a-z\\d]*$", Pattern.CASE_INSENSITIVE);
        if(!Pattern.matches(loginPattern.toString(), user.getUsername())){
            errors.rejectValue("username", "notmatches",
                    "A login can consist of latin letters and numbers, but cannot start with a number");
            return;
        }
        Pattern passwordPattern = Pattern.compile("^[a-z\\d]*$", Pattern.CASE_INSENSITIVE);
        if(!Pattern.matches(passwordPattern.toString(), user.getPassword())){
            errors.rejectValue("password", "notmatches",
                    "Password can only consist of letters of the Latin alphabet and numbers.");
        }
    }
}
