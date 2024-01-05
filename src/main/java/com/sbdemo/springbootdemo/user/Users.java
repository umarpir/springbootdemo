package com.sbdemo.springbootdemo.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String password;
    private LocalDate dob;
    private String cardNumber;
    @Transient
    private int age;
    public Users() {
    }

    public Users(String username, String password, LocalDate dob, String cardNumber) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.cardNumber = cardNumber;
        this.age = Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Users(long id, String username, String password, LocalDate dob, String cardNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.cardNumber = cardNumber;
        this.age = Period.between(this.dob, LocalDate.now()).getYears();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getAge(){
        return age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
