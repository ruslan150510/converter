package ru.test.converter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.test.converter.api.request.CurrencyConverterRequest;
import ru.test.converter.api.response.CurrencyConverterResponse;
import ru.test.converter.repository.ConverterApplicationEntity;
import ru.test.converter.repository.ConverterApplicationRepository;
import ru.test.converter.repository.Currency;

import java.util.concurrent.atomic.AtomicInteger;


@Service
@RequiredArgsConstructor
public class ConverterApplicationService {
    private final ConverterApplicationRepository applicationRepository;

    private AtomicInteger idGenerator = new AtomicInteger(0);

    public CurrencyConverterResponse currencyConverterResponses(
            CurrencyConverterRequest converterRequest) {
        ConverterApplicationEntity entity = new ConverterApplicationEntity();
        int requestId = generateId();
        entity.setUserId(converterRequest.getUserId());
        entity.setAmountInTheOriginalCurrency(converterRequest.getAmountInTheOriginalCurrency());
        entity.setTargetCurrency(converterRequest.getTargetCurrency());
        entity.setSourceСurrency(converterRequest.getSourceСurrency());
        entity.setRequestId(requestId);
        entity.setAmountInTheTargetCurrency(0);
//        double currencyExchangeRate = getCurrencyExchangeRate(
//                converterRequest.getSourceСurrency(),
//                converterRequest.getTargetCurrency());
//        getCurrencyExchangeRate(
//                converterRequest.getSourceСurrency(),
//                converterRequest.getTargetCurrency());
        applicationRepository.getConverterRequest(entity);
        return null;
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }

//    private void getCurrencyExchangeRate(Currency sourceСurrency, Currency targetCurrency) {
//        final RestTemplate restTemplate = new RestTemplate();
//
//        final Post[] posts = restTemplate..getForObject("https://cash.rbc.ru/cash/json/converter_currency_rate/?currency_from=EUR&currency_to=RUR&source=cbrf&sum=1&date=", Post[].class);
//        for (final Post post : posts) {
//            System.out.println(post);
//        }
//
//        final Post postToInsert = Post.builder()
//                .body("bar")
//                .title("foo")
//                .userId(1)
//                .build();
//
//        final Post insertedPost = restTemplate.postForObject("http://jsonplaceholder.typicode.com/posts", postToInsert, Post.class);
//        System.out.println(insertedPost);
////        return null;
//    }
}
