package ru.test.converter.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrencyConverterResponse {
    private int idRequest;
    private double amountInTheTargetCurrency;
}
