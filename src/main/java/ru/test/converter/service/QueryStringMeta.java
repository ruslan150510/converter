package ru.test.converter.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.test.converter.repository.Currency;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class QueryStringMeta {
    @JsonProperty(value = "sum_deal")
    private double sumDeal;
    private String source;
    @JsonProperty(value = "currency_from")
    private Currency currencyFrom;
    @JsonIgnore
    private LocalDateTime date;
    @JsonProperty(value = "currency_to")
    private Currency currencyTo;
}
