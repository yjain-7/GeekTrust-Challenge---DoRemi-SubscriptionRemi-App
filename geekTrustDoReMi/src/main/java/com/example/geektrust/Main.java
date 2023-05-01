package com.example.geektrust;

import com.example.geektrust.applicationconfig.ApplicationConfig;
import com.example.geektrust.command.CommandInvoker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationConfig applicationSetup = ApplicationConfig.getInstanceOfApplicationSetup();
        CommandInvoker commandInvoker = applicationSetup.getCommandInvoker();
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); 
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                List<String> commandParts = Arrays.asList(command.split(" "));
                commandInvoker.executeCommand(commandParts.get(0), commandParts);
            }

            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
