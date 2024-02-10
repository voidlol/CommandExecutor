package ru.voidlol.ce.command.dto;

import ru.voidlol.ce.output.dto.OutputMessageDto;

public record CommandResult(boolean isLoop, OutputMessageDto result) {

    public static CommandResult ofError(Exception e) {
        return new CommandResult(true, new OutputMessageDto(e.getMessage()));
    }
}
