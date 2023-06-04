package funkwhale_player_cli.cli.entity;

import funkwhale_player_cli.cli.CLIUtil;
import funkwhale_player_cli.cli.Screen;
import funkwhale_player_cli.funkwhale.FunkwhaleManager;
import funkwhale_player_cli.playlist.PlayListHelper;
import funkwhale_player_cli.playlist.PlayListManager;

public class BrowserScreen extends Screen {

    private static volatile BrowserScreen instance;

    public static BrowserScreen getInstance() {
        BrowserScreen localInstance = instance;
        if (localInstance == null) {
            synchronized (BrowserScreen.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BrowserScreen();
                }
            }
        }
        return localInstance;
    }

    private BrowserScreen() {}

    @Override
    protected void printInterface() {

    }

    @Override
    protected void printTheOptionsMenu() {
        System.out.println(CLIUtil.YELLOW + "Menu: | (id) Get A Song By Id | (q) Quit | (h) Help |\n"  + CLIUtil.RESET);
    }

    @Override
    protected boolean takeActions(String[] parseInput) {
        switch (parseInput[0]) {
            case "id" -> addTrackById();
            default -> {
                return false;
            }
        }
        return true;
    }

    private void addTrackById() {
        String trackId = getInput("Enter The Track's Id:");
        PlayListHelper.addToPlayList(
                PlayListManager.getInstance().getCurrentPlayList(),
                FunkwhaleManager.getInstance().getTrackById(trackId)
        );
    }

    @Override
    protected void quit() {

    }
}
