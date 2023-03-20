package ru.job4j.dreamjob.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {

    private String name;

    private byte[] content; /*тут кроется различие. доменная модель хранит путь, а не содержимое*/

    public FileDto(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

}