package com.example.geektrust.command;

import java.util.List;

public interface CommandInterface {
    void execute(List<String> commandParts);
}
