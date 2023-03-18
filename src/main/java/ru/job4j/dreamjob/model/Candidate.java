package ru.job4j.dreamjob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Candidate {

    private int id;

    private String name;

    private String description;

    private LocalDateTime creationDate = LocalDateTime.now();

    private int cityId;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Candidate(int id, String name, String description, int cityId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
    }

}
