package ru.job4j.dreamjob.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Candidate {

    private int id;

    private String name;

    private String description;

    private LocalDateTime creationDate = LocalDateTime.now();

    private int cityId;

    private int fileId;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Candidate(int id, String name, String description, int cityId, int fileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
