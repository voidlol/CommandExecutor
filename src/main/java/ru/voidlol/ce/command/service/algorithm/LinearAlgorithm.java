package ru.voidlol.ce.command.service.algorithm;

import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.command.dto.Forecast;
import ru.voidlol.ce.enums.AlgorithmType;

public class LinearAlgorithm implements Algorithm {
    @Override
    public AlgorithmType getType() {
        return AlgorithmType.LINEAR;
    }

    @Override
    public Forecast calculate(RateCommand rateCommand) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
