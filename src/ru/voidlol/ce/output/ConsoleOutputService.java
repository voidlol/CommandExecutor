package ru.voidlol.ce.output;

import ru.voidlol.ce.output.dto.OutputMessageDto;

public class ConsoleOutputService implements OutputService {
    @Override
    public void print(OutputMessageDto dto) {
        System.out.println(dto.message());
    }
}
