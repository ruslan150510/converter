package ru.test.converter.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsersRequestedConversion {
    private int userId;
}
