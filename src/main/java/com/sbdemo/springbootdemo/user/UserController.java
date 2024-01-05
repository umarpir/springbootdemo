package com.sbdemo.springbootdemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(path="/register")
    public ResponseEntity<String> addUser(@RequestBody Users users)  {
        userService.addNewUser(users);
        return ResponseEntity.status(201).body("SUCCESS");

    }

    @DeleteMapping(path="/delete")
    public void deleteUser(@RequestBody Map<String, String> requestBody) {
        userService.deleteUser(requestBody.get("username"));
    }

}
