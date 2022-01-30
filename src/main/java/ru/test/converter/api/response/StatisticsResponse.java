package ru.test.converter.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.test.converter.repository.Currency;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class StatisticsResponse {
    private List<UsersRequestedConversion> usersRequestedConversionList;
    private Map<Integer, Double> usersRequestedConversionListTotalAmount;
    private Set<Currency> currency;
}
