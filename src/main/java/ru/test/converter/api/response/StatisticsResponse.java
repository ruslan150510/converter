package ru.test.converter.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class StatisticsResponse {
    private List<UsersRequestedConversion> usersRequestedConversionList;
    private List<UsersRequestedConversion> usersRequestedConversionListTotalAmount;
    private List<String> currency;
}
