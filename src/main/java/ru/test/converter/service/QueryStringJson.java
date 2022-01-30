package ru.test.converter.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QueryStringJson {
    private int status;
    private QueryStringMeta meta;
    private QueryStringData data;
}
