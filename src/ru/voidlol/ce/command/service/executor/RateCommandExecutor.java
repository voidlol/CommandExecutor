package ru.voidlol.ce.command.service.executor;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.enums.Error;
import ru.voidlol.ce.output.dto.OutputMessageDto;

public class RateCommandExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(Command command) {
        return new CommandResult(true, getResult(command));
    }

    private OutputMessageDto getResult(Command command) {
        if (command instanceof RateCommand rc) {
            String message = String.format("Command: %s\nCurrency: %s\nAlgorithm: %s\nTargetDate: %s\n",
                    rc.getCommandType(),
                    rc.getCurrencyType(),
                    rc.getAlgorithm(),
                    rc.getTargetDate());
            return new OutputMessageDto(message);
        } else {
            throw new RuntimeException(Error.INTERNAL_ERROR.getError());
        }
    }

    @Override
    public CommandType getType() {
        return CommandType.RATE;
    }
}
