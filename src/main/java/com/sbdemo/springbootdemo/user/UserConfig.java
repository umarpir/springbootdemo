package com.sbdemo.springbootdemo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
           Users umar =  new Users("umarpir","password123", LocalDate.of(1999, Month.JANUARY,1), "4659456857412365");
           Users humza =  new Users("humzapir","password123", LocalDate.of(1995, Month.JANUARY,1), "4659456857412365");

           userRepository.saveAll(
                   List.of(umar,humza)
           );
        };
    }
}
