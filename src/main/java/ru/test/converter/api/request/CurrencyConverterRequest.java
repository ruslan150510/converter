package ru.test.converter.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.test.converter.repository.Currency;

@Data
@RequiredArgsConstructor
public class CurrencyConverterRequest {
    @JsonProperty(value = "id")
    private int userId;
    @JsonProperty(value = "amount_currency")
    private double amountInTheOriginalCurrency;
    @JsonProperty(value = "source_currency")
    private Currency sourceСurrency;
    @JsonProperty(value = "target_currency")
    private Currency targetCurrency;
}
