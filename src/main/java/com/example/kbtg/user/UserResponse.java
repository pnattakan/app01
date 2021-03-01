package com.example.kbtg.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private int id;
    private String name;
    private int age;

    public UserResponse() {
    }

    public UserResponse(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    // Setter and Getter methods

}
