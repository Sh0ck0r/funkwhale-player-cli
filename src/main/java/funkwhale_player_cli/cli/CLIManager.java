package funkwhale_player_cli.cli;

import funkwhale_player_cli.ApplicationController;
import funkwhale_player_cli.cli.entity.AuthenticationScreen;
import funkwhale_player_cli.cli.entity.BrowserScreen;
import funkwhale_player_cli.cli.entity.PlayerScreen;

public class CLIManager {

    private PlayerScreen playerScreen;
    private AuthenticationScreen authenticationScreen;
    private BrowserScreen browserScreen;

    public CLIManager(ApplicationController applicationController) {
        Screen.setMainController(applicationController);
        playerScreen = PlayerScreen.getInstance();
        authenticationScreen = AuthenticationScreen.getInstance();
        browserScreen = BrowserScreen.getInstance();
    }

    public PlayerScreen getPlayerScreen() {
        return playerScreen;
    }

    public void startAuthentication() {
        authenticationScreen.show();
    }

    public void showPlayerInterface() {
        playerScreen.show();
    }

    public void openSearchBrowser() {
        browserScreen.show();
    }
}
