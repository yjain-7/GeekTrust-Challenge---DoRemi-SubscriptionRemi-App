package com.example.geektrust.command;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandInvokerTest {

    // Test registering a command
    @Test
    public void testRegisterCommand() {
        CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();
        CommandInterface command = new SomeCommand();
        String commandName = "some-command";

        commandInvoker.register(commandName, command);

        assertEquals(command, commandInvoker.get(commandName));
    }



    // Test getting a non-existent command
    @Test
    public void testGetNonExistentCommand() {
        CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();
        String commandName = "non-existent-command";

        CommandInterface command = commandInvoker.get(commandName);
        assertNull(command);
    }

    // Test retrieving a registered command
    @Test
    public void testRetrieveCommand() {
        CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();
        CommandInterface command = new SomeCommand();
        String commandName = "some-command";

        commandInvoker.register(commandName, command);

        CommandInterface retrievedCommand = commandInvoker.get(commandName);

        assertEquals(command, retrievedCommand);
    }

    // Test executing a null command
    @Test
    public void testExecuteNullCommand() {
        
        CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();
        String commandName = "null-command";

        CommandInterface command = commandInvoker.get(commandName);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }


    private static class SomeCommand implements CommandInterface {
        @Override
        public void execute(List<String> tokens) {
            System.out.println("Executing SomeCommand with tokens: " + tokens);
        }
    }
}
