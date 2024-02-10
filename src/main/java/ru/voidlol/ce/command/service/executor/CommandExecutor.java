package ru.voidlol.ce.command.service.executor;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.enums.CommandType;

public interface CommandExecutor {

    CommandResult execute(Command command);
    CommandType getType();
}
