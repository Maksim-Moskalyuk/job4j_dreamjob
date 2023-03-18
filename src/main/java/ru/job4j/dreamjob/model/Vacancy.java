package ru.job4j.dreamjob.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Vacancy {

    private int id;

    private String title;

    private String description;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean visible;

    private int cityId;

    public Vacancy(int id, String title, String description, LocalDateTime creationDate, boolean visible, int cityId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.visible = visible;
        this.cityId = cityId;
    }

    public Vacancy(int id, String title, String description, LocalDateTime creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}