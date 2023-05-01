package com.example.geektrust.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private CommandInvoker() {
    }

    static CommandInvoker commandInvoker = new CommandInvoker();

    public static CommandInvoker getInstanceOfCommandInvoker() {
        return commandInvoker;
    }

    private static final Map<String, CommandInterface> commandMap = new HashMap<>();

    public void register(String commandName, CommandInterface command) {
        commandMap.put(commandName, command);
    }

    public CommandInterface get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) {
        CommandInterface command = get(commandName);
        command.execute(tokens);
    }

}
