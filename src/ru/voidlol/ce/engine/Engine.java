package ru.voidlol.ce.engine;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.dto.CommandResult;
import ru.voidlol.ce.command.service.ExecutorService;
import ru.voidlol.ce.config.AppConfig;
import ru.voidlol.ce.enums.Message;
import ru.voidlol.ce.input.InputService;
import ru.voidlol.ce.output.OutputService;
import ru.voidlol.ce.output.dto.OutputMessageDto;
import ru.voidlol.ce.parser.ParserService;

import java.util.Optional;

public class Engine {
    private final OutputService outputService;
    private final InputService inputService;
    private final ExecutorService executorService;
    private final ParserService parserService;

    private Engine(AppConfig config) {
        this.outputService = config.getImplementation(OutputService.class);
        this.inputService = config.getImplementation(InputService.class);
        this.executorService = config.getImplementation(ExecutorService.class);
        this.parserService = config.getImplementation(ParserService.class);
    }

    public static void run() {
        new Engine(new AppConfig()).loop();
    }

    private void loop() {
        boolean isLoop = true;
        while (isLoop) {
            askForCommand();
            CommandResult commandResult = processCommand();
            printResult(commandResult.result());
            isLoop = commandResult.isLoop() && askForContinue();
        }
    }

    private boolean askForContinue() {
        this.outputService.print(new OutputMessageDto(Message.ASK_FOR_CONTINUE.getMessage()));
        String userInput = this.inputService.getUserInput();
        return userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes");
    }

    private void printResult(OutputMessageDto result) {
        Optional.ofNullable(result).ifPresent(this.outputService::print);
    }

    private CommandResult processCommand() {
        try {
            String userInput = this.inputService.getUserInput();
            Command command = this.parserService.parse(userInput);
            return executorService.execute(command);
        } catch (Exception e) {
            return CommandResult.ofError(e);
        }
    }

    private void askForCommand() {
        this.outputService.print(new OutputMessageDto(Message.ASK_FOR_COMMAND.getMessage()));
    }

}
