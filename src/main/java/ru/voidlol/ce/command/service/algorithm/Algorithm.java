package ru.voidlol.ce.command.service.algorithm;

import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.command.dto.Forecast;
import ru.voidlol.ce.enums.AlgorithmType;

public interface Algorithm {

    AlgorithmType getType();

    Forecast calculate(RateCommand rateCommand);
}
