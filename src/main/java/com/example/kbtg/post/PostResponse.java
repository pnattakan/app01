package com.example.kbtg.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostResponse {
    private int id;
    private int userId;
    private String title;
    private String body;

}
