package ru.job4j.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneratorString {

    public String generateLogin() {
        return RandomStringUtils.randomAlphabetic(7);
    }

    public String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
