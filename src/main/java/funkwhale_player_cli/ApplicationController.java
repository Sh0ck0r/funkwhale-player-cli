package funkwhale_player_cli;

import funkwhale_player_cli.funkwhale.FunkwhaleManager;
import funkwhale_player_cli.player.PlayerManager;
import funkwhale_player_cli.cli.CLIManager;
import funkwhale_player_cli.cache.CacheManager;

import java.util.Scanner;

public class ApplicationController {

    private CLIManager cliManager;
    private FunkwhaleManager funkwhaleManager;
    private PlayerManager playerManager;
    private CacheManager cacheManager;
    private Scanner scanner;

    public ApplicationController() {
        cliManager = new CLIManager(this);
        funkwhaleManager = FunkwhaleManager.getInstance();
        playerManager = PlayerManager.getInstance();
        cacheManager = new CacheManager();
        scanner = new Scanner(System.in);
        printGreetings();
        start();
    }

    public CLIManager getCliManager() {
        return cliManager;
    }

    public FunkwhaleManager getFunkwhaleManager() {
        return funkwhaleManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public Scanner getScanner() {
        scanner.reset();
        return scanner;
    }

    private void start() {
        cliManager.getPlayerScreen().setPlayerManager(playerManager);

        if (!cacheManager.isThereRecentPod()) {
            cliManager.startAuthentication();
        }
    }

    public void openPlayer() {
        cliManager.showPlayerInterface();
    }

    private void printGreetings() {
        System.out.println("# ====================================================== #");
        System.out.println("#           Welcome to Funkwhale Player CLI!             #");
        System.out.println("# ====================================================== #\n");
    }

    public void exit() {
        System.exit(0);
    }
}
