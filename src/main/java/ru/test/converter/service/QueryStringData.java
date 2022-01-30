package ru.test.converter.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QueryStringData {
    private String date;
    @JsonProperty(value = "sum_result")
    private double sumResult;
    private double rate1;
    private double rate2;
}
