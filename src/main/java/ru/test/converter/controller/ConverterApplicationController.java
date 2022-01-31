package ru.test.converter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.test.converter.api.request.CurrencyConverterRequest;
import ru.test.converter.api.response.CurrencyConverterResponse;
import ru.test.converter.api.response.StatisticsResponse;
import ru.test.converter.service.ConverterApplicationService;

@RestController
@RequiredArgsConstructor
public class ConverterApplicationController {
    private final ConverterApplicationService applicationService;

    @PostMapping("/exchange")
    public CurrencyConverterResponse responseList(
            @RequestBody CurrencyConverterRequest currencyConverterRequest) {
        return applicationService.currencyConverterResponses(currencyConverterRequest);
    }

    @GetMapping("/stats")
    public StatisticsResponse statisticsResponseCollection() {
        return applicationService.getStatistics();
    }
}
