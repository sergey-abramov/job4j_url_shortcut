package ru.job4j.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthErrorDto {
    private int status;
    private String message;
}
