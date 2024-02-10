package ru.voidlol.ce.command.service.executor;

import lombok.RequiredArgsConstructor;
import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.command.dto.Forecast;
import ru.voidlol.ce.command.service.algorithm.Algorithm;
import ru.voidlol.ce.enums.AlgorithmType;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.enums.Error;
import ru.voidlol.ce.output.dto.OutputMessageDto;

import java.util.Map;

@RequiredArgsConstructor
public class RateCommandExecutor implements CommandExecutor {

    private final Map<AlgorithmType, Algorithm> algorithmMap;
    @Override
    public CommandResult execute(Command command) {
        return new CommandResult(true, getResult(command));
    }

    private OutputMessageDto getResult(Command command) {
        if (command instanceof RateCommand rc) {
            Forecast forecast = calculateForecast(rc);
            return new OutputMessageDto(forecast.toString());
//            String message = String.format("Command: %s\nCurrency: %s\nAlgorithm: %s\nTargetDate: %s\n",
//                    rc.getCommandType(),
//                    rc.getCurrencyType(),
//                    rc.getAlgorithm(),
//                    rc.getTargetDate());
//            return new OutputMessageDto(message);
        } else {
            throw new RuntimeException(Error.INTERNAL_ERROR.getError());
        }
    }

    private Forecast calculateForecast(RateCommand command) {
        return algorithmMap.get(command.getAlgorithmType()).calculate(command);
    }

    @Override
    public CommandType getType() {
        return CommandType.RATE;
    }
}
