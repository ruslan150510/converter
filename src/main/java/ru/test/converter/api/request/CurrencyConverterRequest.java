package ru.test.converter.api.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.test.converter.repository.Currency;

@Data
@RequiredArgsConstructor
public class CurrencyConverterRequest {
    private int userId;
    private double amountInTheOriginalCurrency;
    private Currency sourceСurrency;
    private Currency targetCurrency;
}
