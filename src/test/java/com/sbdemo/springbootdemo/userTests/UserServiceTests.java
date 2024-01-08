package com.sbdemo.springbootdemo.userTests;

import com.sbdemo.springbootdemo.Exceptions.RegistrationValidationException;
import com.sbdemo.springbootdemo.Exceptions.UsernameTakenException;
import com.sbdemo.springbootdemo.user.UserRepository;
import com.sbdemo.springbootdemo.user.UserService;
import com.sbdemo.springbootdemo.user.Users;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Users users;

    private Users mockUser1 = new Users(1,"testuser1","test123", LocalDate.now(),"1234567890123456");
    private Users mockUser2 = new Users(2,"testuser2","test123", LocalDate.now(),"6543210987654321");
    private Users validUser = new Users("testuser123","TestPassword123", LocalDate.of(2000,1,1),"6543210987654321");
    private Users invalidUser = new Users("testuser1","test123", LocalDate.now(),"654321098");




    @Test
    public void getusersTest(){
        when(userRepository.findAll()).thenReturn(List.of(mockUser1,mockUser2));
        assertEquals(List.of(mockUser1,mockUser2), userService.getUsers());
    }

    @Test
    public void when_invalid_user_is_given_then_user_is_not_added(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(mockUser1));
        assertThrows(UsernameTakenException.class, () -> userService.addNewUser(invalidUser) );
    }

    @Test
    public void when_valid_user_is_given_then_user_is_added(){
        userService.addNewUser(validUser);
        verify(userRepository,times(1)).save(validUser);
    }

    @Test
    public void when_valid_username_is_given_to_delete_user_then_delete_user(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(mockUser1));
        userService.deleteUser("testuser1");
        verify(userRepository,times(1)).delete(mockUser1);
    }

    @Test
    public void when_invalid_username_is_given_to_delete_user_then_throws_throwsEntityNotFoundException(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser("testuser1"), "User with username 'testuser1' not found");
    }
}
