package ru.voidlol.ce.command.service.algorithm;

import lombok.RequiredArgsConstructor;
import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.command.dto.Forecast;
import ru.voidlol.ce.data.domain.CurrencyRate;
import ru.voidlol.ce.data.repository.CurrencyRepository;
import ru.voidlol.ce.enums.AlgorithmType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StraightAlgorithm implements Algorithm {

    private final CurrencyRepository currencyRepository;
    private static final int RATES_TO_ACCOUNT = 30;

    @Override
    public AlgorithmType getType() {
        return AlgorithmType.STRAIGHT;
    }

    @Override
    public Forecast calculate(RateCommand rateCommand) {
        List<CurrencyRate> knownCurrencies = currencyRepository.findLastNByType(RATES_TO_ACCOUNT, rateCommand.getCurrencyType());
        List<CurrencyRate> filledCurrencies = fillMissingCurrencies(knownCurrencies);
        double averageDelta = filledCurrencies.stream()
                .mapToDouble(CurrencyRate::getRate)
                .skip(1)
                .map(previousValue -> previousValue - filledCurrencies.get(0).getRate())
                .average()
                .orElse(0.0);
        LocalDate lastKnownDate = filledCurrencies.get(0).getDate();
        double lastKnownRate = filledCurrencies.get(0).getRate();
        while (lastKnownDate.isBefore(rateCommand.getTargetDate())) {
            lastKnownDate = lastKnownDate.plusDays(1);
            lastKnownRate += averageDelta;
        }
        return new Forecast(lastKnownRate, rateCommand.getTargetDate(), rateCommand.getCurrencyType());
    }

    private List<CurrencyRate> fillMissingCurrencies(List<CurrencyRate> knownCurrencies) {
        knownCurrencies.sort(Comparator.comparing(CurrencyRate::getDate));
        List<CurrencyRate> filledCurrencies = new ArrayList<>();
        CurrencyRate prev = knownCurrencies.get(0);
        for (CurrencyRate current : knownCurrencies) {
            LocalDate expectedNextDay = prev.getDate().plusDays(1);
            while (expectedNextDay.isBefore(current.getDate())) {
                prev = prev.copyAtDate(expectedNextDay);
                filledCurrencies.add(prev);
                expectedNextDay = prev.getDate().plusDays(1);
            }
            filledCurrencies.add(current);
            prev = current;
        }
        return filledCurrencies.stream()
                .sorted(Comparator.comparing(CurrencyRate::getDate).reversed())
                .limit(RATES_TO_ACCOUNT)
                .collect(Collectors.toList());
    }
}
