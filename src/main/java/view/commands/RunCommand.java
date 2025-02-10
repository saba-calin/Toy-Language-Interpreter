package view.commands;

import controller.Controller;

public class RunCommand extends Command {
    private Controller controller;

    public RunCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            this.controller.allSteps();
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
