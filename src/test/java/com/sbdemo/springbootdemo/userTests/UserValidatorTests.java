package com.sbdemo.springbootdemo.userTests;

import com.sbdemo.springbootdemo.Exceptions.RegistrationValidationException;
import com.sbdemo.springbootdemo.Exceptions.UnderAgeException;
import com.sbdemo.springbootdemo.Exceptions.UsernameTakenException;
import com.sbdemo.springbootdemo.user.UserRepository;
import com.sbdemo.springbootdemo.user.UserValidator;
import com.sbdemo.springbootdemo.user.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class UserValidatorTests {
    @Autowired
    UserValidator userValidator;
    @MockBean
    UserRepository userRepository;

    @Test
    public void when_user_has_invalid_username_throws_RegistrationValidationException(){
        Users mockUser = new Users("test user", "Password123", LocalDate.of(2000, 1, 1), "1234567890123456");
        assertThrows(RegistrationValidationException.class, () -> userValidator.validateUserFields(mockUser),"Username must be alphanumeric with no spaces");
    }

    @Test
    public void when_user_has_valid_details_should_void(){
        Users mockUser = new Users("testuser", "Password123", LocalDate.of(2000, 1, 1), "1234567890123456");
        assertDoesNotThrow(() -> userValidator.validateUserFields(mockUser));
    }

    @Test
    public void when_user_has_invalid_password_throws_RegistrationValidationException(){
        Users mockUser = new Users("testuser", "123", LocalDate.of(2000, 1, 1), "1234567890123456");
        assertThrows(RegistrationValidationException.class, () -> userValidator.validateUserFields(mockUser),"Password must be at least 4 characters, include one uppercase letter, one lowercase letter, and one number");
    }

    @Test
    public void when_user_has_invalid_cardNumber_throws_RegistrationValidationException(){
        Users mockUser = new Users("testuser", "Password123", LocalDate.of(2000, 1, 1), "123456");
        assertThrows(RegistrationValidationException.class, () -> userValidator.validateUserFields(mockUser),"Payment Card Number must be between 15 and 19 digits"

        );
    }

    @Test
    public void when_user_has_invalid_age_throws_UnderAgeException(){
        Users mockUser = new Users("testuser", "Password123", LocalDate.of(2015, 1, 1), "1234567890123456");
        assertThrows(UnderAgeException.class, () -> userValidator.validateUserAge(mockUser)

        );
    }
    @Test
    public void when_user_has_username_that_exists_throws_UsernameTakenException(){
        Users mockUser = new Users("testuser", "Password123", LocalDate.of(2000, 1, 1), "1234567890123456");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        assertThrows(UsernameTakenException.class, () -> userValidator.validUsername(mockUser)
        );
    }


}
