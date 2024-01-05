package com.sbdemo.springbootdemo.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers(){
     return userRepository.findAll();
        }

    public void addNewUser(Users users){
        Optional<Users> user = userRepository.findByUsername(users.getUsername());
        if(user.isPresent()) {
            throw new IllegalStateException("username taken!");
        }
        userRepository.save(users);
    }

    public void deleteUser(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        users.ifPresentOrElse(userRepository::delete, () -> {
            throw new EntityNotFoundException("User with username '" + username + "' not found");
        });
    }

}
