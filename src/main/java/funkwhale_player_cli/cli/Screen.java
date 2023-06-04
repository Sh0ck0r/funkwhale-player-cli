package funkwhale_player_cli.cli;

import funkwhale_player_cli.ApplicationController;

public abstract class Screen {

    private static ApplicationController applicationController;

    public static void setMainController(ApplicationController applicationController) {
        Screen.applicationController = applicationController;
    }

    public ApplicationController getMainController() {
        return applicationController;
    }

    public void show() {
        String userInput;
        //setArgs(args);
        do {
            printTheOptionsMenu();
            printInterface();
            userInput = takeUserInput();
            if (!takeActions(CLIUtil.parseInput(userInput))) {
                switch (userInput) {
                    case "q" -> quit();
                    case "exit" -> applicationController.exit();
                    default -> System.out.println("Invalid input");
                }
            }
        } while (!userInput.equalsIgnoreCase("q"));
    }

    //protected abstract void setArgs(Object ... args);
    protected abstract void printInterface();
    protected abstract void printTheOptionsMenu();
    protected String getInput(String message) {
        System.out.print(message);
        return applicationController.getScanner().nextLine();
    }
    protected String takeUserInput() {
        System.out.print("> ");
        return applicationController.getScanner().nextLine();
    }
    protected abstract boolean takeActions(String[] parseInput);
    protected abstract void quit();
}
