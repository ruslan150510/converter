package ru.test.converter.repository;

import org.springframework.stereotype.Repository;
import ru.test.converter.api.response.CurrencyConverterResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ConverterApplicationRepository {
    private final Map<Currency, Integer> currencyEntities = new ConcurrentHashMap<>();
    private final List<ConverterApplicationEntity> applicationEntityList = new ArrayList<>();

    public CurrencyConverterResponse getConverterRequest(
            ConverterApplicationEntity converterApplicationEntity) {
        CurrencyConverterResponse response = new CurrencyConverterResponse();
        response.setIdRequest(applicationEntityList.size());
        applicationEntityList.add(converterApplicationEntity);
        setCurrencyEntities(converterApplicationEntity.getTargetCurrency());
        setCurrencyEntities(converterApplicationEntity.getSourceСurrency());
        response.setAmountInTheTargetCurrency(converterApplicationEntity.getAmountInTheOriginalCurrency());
        return response;
    }

    public void setCurrencyEntities(Currency currency) {
        int count = 1;
        if (currencyEntities.containsKey(currency)){
            count =+ currencyEntities.get(currency);
        }
        currencyEntities.put(currency, count);
    }
}
