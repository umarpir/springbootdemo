package com.sbdemo.springbootdemo.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<Users> getUsers(){
     return userRepository.findAll();
        }

    public void addNewUser(Users users){
        userValidator.validUsername(users);
        userValidator.validateUserAge(users);
        userValidator.validateUserFields(users);
        userRepository.save(users);

    }

    public void deleteUser(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        users.ifPresentOrElse(userRepository::delete, () -> {
            throw new EntityNotFoundException("User with username '" + username + "' not found");
        });
    }

}
