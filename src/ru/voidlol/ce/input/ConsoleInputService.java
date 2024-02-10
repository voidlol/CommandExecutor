package ru.voidlol.ce.input;

import java.util.Scanner;

public class ConsoleInputService implements InputService {

    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }
}
