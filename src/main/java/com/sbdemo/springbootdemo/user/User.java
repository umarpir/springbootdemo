package com.sbdemo.springbootdemo.user;

import java.time.LocalDate;

public class User {
    private long id;
    private String username;
    private String password;
    private LocalDate dob;
    private String cardNumber;
    public User() {
    }

    public User(String username, String password, LocalDate dob, String cardNumber) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.cardNumber = cardNumber;
    }

    public User(long id, String username, String password, LocalDate dob, String cardNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.cardNumber = cardNumber;
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
