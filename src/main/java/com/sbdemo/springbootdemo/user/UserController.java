package com.sbdemo.springbootdemo.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void registerUser(@RequestBody Users users) {
        userService.addNewUser(users);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Map<String, String> requestBody) {
        userService.deleteUser(requestBody.get("username"));
    }

}
