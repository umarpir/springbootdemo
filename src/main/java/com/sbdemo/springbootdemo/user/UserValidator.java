package com.sbdemo.springbootdemo.user;

import com.sbdemo.springbootdemo.Exceptions.RegistrationValidationException;
import com.sbdemo.springbootdemo.Exceptions.UnderAgeException;
import com.sbdemo.springbootdemo.Exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validateUserFields(Users users) throws RegistrationValidationException {
        if (!users.getUsername().matches("[a-zA-Z0-9]+")) {
            throw new RegistrationValidationException("Username must be alphanumeric with no spaces");
        }

        if (!users.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,}$")) {
            throw new RegistrationValidationException("Password must be at least 4 characters, include one uppercase letter, one lowercase letter, and one number");
        }

        if (users.getDob() == null){
            throw new RegistrationValidationException("Date of Birth must be in ISO 8601 format (YYYY-MM-DD)");
        }

        if (!users.getCardNumber().matches("\\d{15,19}")) {
            throw new RegistrationValidationException("Payment Card Number must be between 15 and 19 digits");
        }

    }

    public void validUsername(Users users){
        Optional<Users> user = userRepository.findByUsername(users.getUsername());
        if(user.isPresent()) {
            throw new UsernameTakenException("username is taken");
        }
    }

    public void validateUserAge(Users users) {

        if (users.getAge() < 18) {
            throw new UnderAgeException("Must be Over 18 to register");
        }
    }

}
