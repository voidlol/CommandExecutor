package ru.voidlol.ce.command.service;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.dto.CommandResult;

public interface ExecutorService {

    CommandResult execute(Command command);
}
