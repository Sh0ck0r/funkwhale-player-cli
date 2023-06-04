package funkwhale_player_cli.cli.entity;

import funkwhale_player_cli.cli.Screen;
import funkwhale_player_cli.cli.CLIUtil;
import funkwhale_player_cli.funkwhale.FunkwhaleManager;

import java.util.Objects;

public class AuthenticationScreen extends Screen {

    private static volatile AuthenticationScreen instance;

    public static AuthenticationScreen getInstance() {
        AuthenticationScreen localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthenticationScreen.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AuthenticationScreen();
                }
            }
        }
        return localInstance;
    }

    private AuthenticationScreen() {}

    public void start() {
        printTheOptionsMenu();
        printInterface();
    }

    @Override
    protected void printInterface() {
        System.out.println("Please, provide your Funkwhale Pod:");
    }

    @Override
    protected void printTheOptionsMenu() {
        System.out.println(CLIUtil.YELLOW + "Menu: | (q) Quit | (h) Help |\n" + CLIUtil.RESET);
    }

    @Override
    protected boolean takeActions(String[] parseInput) {
        String podUrl = parseInput[0];
        if (!Objects.equals(podUrl, "q") && !Objects.equals(podUrl, "exit")) {
            setUpPodURL(podUrl);
            return true;
        }

        return false;
    }

    private void setUpPodURL(String podUrl) {
        FunkwhaleManager.getInstance().setPodURL(podUrl);
        super.getMainController().openPlayer();
    }

    @Override
    protected void quit() {}
}
