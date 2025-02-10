package view;

import view.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        this.commands.put(command.getKey(), command);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print(">");

            String key = scanner.nextLine();
            Command command = commands.get(key);
            if (command == null) {
                System.out.println("Invalid option");
                continue;
            }

            command.execute();
        }
    }

    private void printMenu() {
        System.out.println();
        for (Command command : commands.values()) {
            System.out.println(command.getKey() + " : " + command.getDescription());
        }
    }
}
