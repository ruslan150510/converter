package ru.test.converter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.test.converter.api.request.CurrencyConverterRequest;
import ru.test.converter.api.response.CurrencyConverterResponse;
import ru.test.converter.api.response.StatisticsResponse;
import ru.test.converter.api.response.UsersRequestedConversion;
import ru.test.converter.repository.ConverterApplicationEntity;
import ru.test.converter.repository.ConverterApplicationRepository;
import ru.test.converter.repository.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@RequiredArgsConstructor
public class ConverterApplicationService {
    private final ConverterApplicationRepository applicationRepository;
    private static final String PATH = "https://cash.rbc.ru/cash/json/converter_currency_rate/?" +
            "currency_from=currencyFrom&currency_to=currencyTo&source=cbrf&sum=1&date=";
    private static final Currency USD = Currency.USD;
    private static final int ONE_REQUEST_IS_MORE_THAN_10000 = 10000;
    private static final int AMOUNT_IS_MORE_THAN_100000 = 100000;

    private AtomicInteger idGenerator = new AtomicInteger(1);

    public CurrencyConverterResponse currencyConverterResponses(
            CurrencyConverterRequest converterRequest) {
        ConverterApplicationEntity entity = new ConverterApplicationEntity();
        int requestId = generateId();
        entity.setUserId(converterRequest.getUserId());
        entity.setAmountInTheOriginalCurrency(converterRequest.getAmountInTheOriginalCurrency());
        entity.setTargetCurrency(converterRequest.getTargetCurrency());
        entity.setSourceСurrency(converterRequest.getSourceСurrency());
        entity.setRequestId(requestId);
        double currencyExchangeRate = getCurrencyExchangeRate(
                converterRequest.getSourceСurrency(),
                converterRequest.getTargetCurrency(),
                converterRequest.getAmountInTheOriginalCurrency());
        entity.setAmountInTheTargetCurrency(currencyExchangeRate);
        return applicationRepository.setApplicationEntityList(entity);
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }

    private double getCurrencyExchangeRate(Currency sourceСurrency,
                                           Currency targetCurrency,
                                           double totalAmount) {
        final RestTemplate restTemplate = new RestTemplate();
        String urlPath = PATH.toString().replace("currencyFrom", sourceСurrency.name())
                .replace("currencyTo", targetCurrency.name());
        final QueryStringJson restTemplateForObject =
                restTemplate.getForObject(urlPath, QueryStringJson.class);
        return totalAmount * restTemplateForObject.getData().getRate1();
    }

    public StatisticsResponse getStatistics() {
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setCurrency(applicationRepository.getCurrencyEntites());
        List<UsersRequestedConversion> usersRequestedConversions = new ArrayList<>();
        Map<Integer, Double> convertionTotalAmounts = new ConcurrentHashMap<>();
        fillingInStatistics(usersRequestedConversions, convertionTotalAmounts);
        statisticsResponse.setUsersRequestedConversionList(usersRequestedConversions);
        statisticsResponse.setUsersRequestedConversionListTotalAmount(convertionTotalAmounts);
        return statisticsResponse;
    }

    public void fillingInStatistics(List<UsersRequestedConversion> usersRequestedConversions,
                                    Map<Integer, Double> usersRequestConvertionTotalAmounts) {
        List<ConverterApplicationEntity> conversionList = applicationRepository.getApplicationEntityList();
        double transferredCurrencyInUsd;
        for (int i = 0; i < conversionList.size(); i++) {
            UsersRequestedConversion usersRequestedConversion =
                    new UsersRequestedConversion();
            if (conversionList.get(i).getSourceСurrency() == USD) {
                transferredCurrencyInUsd = conversionList.get(i).getAmountInTheOriginalCurrency();
                if (conversionList.get(i).getAmountInTheOriginalCurrency() >=
                        ONE_REQUEST_IS_MORE_THAN_10000) {
                    usersRequestedConversion.setUserId(conversionList.get(i).getUserId());
                }
            } else {
                if (conversionList.get(i).getTargetCurrency() == USD) {
                    transferredCurrencyInUsd = conversionList.get(i).getAmountInTheTargetCurrency();
                } else {
                    transferredCurrencyInUsd = getCurrencyExchangeRate(
                            conversionList.get(i).getSourceСurrency(),
                            USD, conversionList.get(i).getAmountInTheOriginalCurrency());
                }
                if (transferredCurrencyInUsd >=
                        ONE_REQUEST_IS_MORE_THAN_10000) {
                    usersRequestedConversion.setUserId(conversionList.get(i).getUserId());
                }
            }
            usersRequestedConversions.add(usersRequestedConversion);
            if (usersRequestConvertionTotalAmounts.equals(conversionList.get(i).getUserId())) {
                usersRequestConvertionTotalAmounts.put(conversionList.get(i).getUserId(),
                        usersRequestConvertionTotalAmounts.get(conversionList.get(i).getUserId())
                                + transferredCurrencyInUsd);
            } else {
                usersRequestConvertionTotalAmounts.put(conversionList.get(i).getUserId(),
                        transferredCurrencyInUsd);
            }
        }
    }
}
