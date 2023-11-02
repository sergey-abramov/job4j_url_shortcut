package ru.job4j.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private boolean flagRegistrationBefore;
    private String login;
    private String password;

}
