package com.sbdemo.springbootdemo.userTests;

import com.sbdemo.springbootdemo.user.UserRepository;
import com.sbdemo.springbootdemo.user.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRespositoryTests {
    Users mockUser = new Users("testuser", "password", LocalDate.of(2000, 1, 1), "1234567890123456");
    @MockBean
    UserRepository userRepository;

    @Test
    public void findByUsername_existingUser_returnsUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        Optional<Users> foundUser = userRepository.findByUsername("testuser");
        assertTrue(foundUser.isPresent());
        assertEquals(mockUser, foundUser.get());
    }
}
