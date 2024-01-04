package com.sbdemo.springbootdemo.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service
public class UserService {

    public List<Users> getUsers(){
        return List.of(new Users(1L,"umarpir","password123", LocalDate.of(1999, Month.JANUARY,1), "4659456857412365"));
    }

}
