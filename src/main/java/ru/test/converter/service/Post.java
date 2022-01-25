package ru.test.converter.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private int id;
    private String title;
    private String body;
    private int userId;

    public String toString() {
        return String.format("\n id: %s \n title: %s \n body: %s \n userId: %s \n", id, title, body, userId);
    }
}

