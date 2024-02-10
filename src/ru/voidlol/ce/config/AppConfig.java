package ru.voidlol.ce.config;

import ru.voidlol.ce.command.service.ExecutorService;
import ru.voidlol.ce.command.service.ExecutorServiceImpl;
import ru.voidlol.ce.command.service.executor.CommandExecutor;
import ru.voidlol.ce.command.service.executor.ExitCommandExecutor;
import ru.voidlol.ce.command.service.executor.RateCommandExecutor;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.input.ConsoleInputService;
import ru.voidlol.ce.input.InputService;
import ru.voidlol.ce.output.ConsoleOutputService;
import ru.voidlol.ce.output.OutputService;
import ru.voidlol.ce.parser.ParserService;
import ru.voidlol.ce.parser.ParserServiceImpl;
import ru.voidlol.ce.parser.parser.CommandParser;
import ru.voidlol.ce.parser.parser.ExitCommandParser;
import ru.voidlol.ce.parser.parser.RateCommandParser;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppConfig {

    private final Map<Class<?>, Object> implementationMap = new HashMap<>();

    public AppConfig() {
        configureInputService();
        configureOutputService();
        configureExecutorService();
        configureCommandParser();
    }

    private void configureCommandParser() {
        Map<CommandType, CommandParser> validatorMap = Stream.of(
                new RateCommandParser(), new ExitCommandParser()
        ).collect(Collectors.toMap(CommandParser::getType, Function.identity()));
        ParserService parserService = new ParserServiceImpl(validatorMap);
        implementationMap.put(ParserService.class, parserService);
    }

    private void configureExecutorService() {
        Map<CommandType, CommandExecutor> commandExecutorMap = Stream.of(
                new RateCommandExecutor(), new ExitCommandExecutor()
        ).collect(Collectors.toMap(CommandExecutor::getType, Function.identity()));
        implementationMap.put(ExecutorService.class, new ExecutorServiceImpl(commandExecutorMap));
    }

    private void configureOutputService() {
        OutputService consoleOutputService = new ConsoleOutputService();
        implementationMap.put(OutputService.class, consoleOutputService);
    }

    private void configureInputService() {
        InputService consoleInputService = new ConsoleInputService();
        implementationMap.put(InputService.class, consoleInputService);
    }

    public <T> T getImplementation(Class<T> clazz) {
        return Optional.ofNullable(implementationMap.get(clazz))
                .map(clazz::cast)
                .orElseThrow(() -> new RuntimeException("Implementation not found for " + clazz.getName()));
    }
}
