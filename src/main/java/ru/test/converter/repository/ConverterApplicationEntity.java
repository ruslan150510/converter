package ru.test.converter.repository;

import lombok.Data;

@Data
public class ConverterApplicationEntity {
    private int userId;
    private double amountInTheOriginalCurrency;
    private Currency sourceСurrency;
    private Currency targetCurrency;
    private int requestId;
    private double amountInTheTargetCurrency;
}
