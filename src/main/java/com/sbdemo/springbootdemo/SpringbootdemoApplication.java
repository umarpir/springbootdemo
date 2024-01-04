package com.sbdemo.springbootdemo;

import com.sbdemo.springbootdemo.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@GetMapping
	public List<User> hello(){
		return List.of(new User(1L,"umarpir","password123", LocalDate.of(1999, Month.JANUARY,1), "4659456857412365"));
	}

}
