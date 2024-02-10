package ru.voidlol.ce.command.service.executor;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.enums.CommandType;

public class ExitCommandExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(Command command) {
        return new CommandResult(false, null);
    }

    @Override
    public CommandType getType() {
        return CommandType.EXIT;
    }
}
