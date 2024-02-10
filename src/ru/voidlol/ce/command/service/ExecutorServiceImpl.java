package ru.voidlol.ce.command.service;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.command.service.executor.CommandExecutor;
import ru.voidlol.ce.enums.CommandType;

import java.util.Map;

public class ExecutorServiceImpl implements ExecutorService {

    private final Map<CommandType, CommandExecutor> executorMap;

    public ExecutorServiceImpl(Map<CommandType, CommandExecutor> executorMap) {
        this.executorMap = executorMap;
    }

    @Override
    public CommandResult execute(Command command) {
        return executorMap.get(command.getCommandType()).execute(command);
    }
}
