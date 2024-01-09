package com.sbdemo.springbootdemo.userTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbdemo.springbootdemo.user.UserController;
import com.sbdemo.springbootdemo.user.UserRepository;
import com.sbdemo.springbootdemo.user.UserService;
import com.sbdemo.springbootdemo.user.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.datatype.jsr310.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import

        static org.mockito.Mockito.when;
import

        static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import

        static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    Users mockUser = new Users("testuser2", "Password1234", LocalDate.of(2000, 1, 1), "1234567890123456");


    @Test
    public void getUsers_returnsAllUsers() throws Exception {
        List<Users> mockUsers =List.of(new Users("testuser1", "password123", LocalDate.of(2000, 1, 1), "1234567890123456"));
        when(userService.getUsers()).thenReturn(mockUsers);
        mvc.perform(get("/api/v1/user"));
        Mockito.verify(userService).getUsers();

        //mvc.perform(get("/api/v1/user")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].username", equalTo("testuser1")));
    }

    @Test
    public void addUser_createsNewUser() throws Exception {
        //when(userService.addNewUser(mockUser)).then(doNothing());
        Path jsonFilePath = Paths.get("src/test/java/com/sbdemo/springbootdemo/userTests/userRegistrationMock.json");
        String jsonContent = Files.readString(jsonFilePath);
        MockHttpServletResponse response = mvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        assertEquals("SUCCESS", response.getContentAsString());
    }

    @Test
    public void deleteUser_removesUser() throws Exception {
        when(userRepository.findByUsername(any())).thenReturn(Optional.ofNullable(mockUser));
        mvc.perform(delete("/api/v1/user/delete").contentType(MediaType.APPLICATION_JSON).content("{\"username\": \"testuser1\"}")).andExpect(status().isOk());

        Mockito.verify(userService).deleteUser("testuser1");
    }

    private static String asJsonString(final Object obj) {
        try {
            return

                    new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw

                    new RuntimeException(e);
        }
    }

}
