package ru.job4j.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StatisticDto {

    private Map<String, Integer> statisticForUrl = new HashMap<>();
}
