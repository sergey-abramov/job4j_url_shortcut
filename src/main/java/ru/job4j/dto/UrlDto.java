package ru.job4j.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlDto {

    private int status;
    private String message;
}
