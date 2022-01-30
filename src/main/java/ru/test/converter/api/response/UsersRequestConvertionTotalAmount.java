package ru.test.converter.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsersRequestConvertionTotalAmount {
    private int userId;
    private double totalAmountCurrency;
}
