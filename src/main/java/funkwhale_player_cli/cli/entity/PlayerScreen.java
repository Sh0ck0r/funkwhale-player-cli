package funkwhale_player_cli.cli.entity;

import funkwhale_player_cli.player.PlayerManager;
import funkwhale_player_cli.cli.Screen;
import funkwhale_player_cli.cli.CLIUtil;
import funkwhale_player_cli.playlist.PlayListManager;

public class PlayerScreen extends Screen {

    private static volatile PlayerScreen instance;
    private PlayerManager playerManager;

    public static PlayerScreen getInstance() {
        PlayerScreen localInstance = instance;
        if (localInstance == null) {
            synchronized (PlayerScreen.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlayerScreen();
                }
            }
        }
        return localInstance;
    }

    private PlayerScreen() {}

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    private void printCurrentPlayList() {
        PlayListManager.getInstance().getCurrentPlayList().print();
    }

    private void printPlayingTrack(int currentIndex) {
        String p = "Playing: " +
                (currentIndex != -1 ? PlayListManager.getInstance().getCurrentPlayList().getItem(currentIndex).toString() : "null") + " " + "{MODE}";
        String s = "-".repeat(p.length());
        System.out.println(s);
        System.out.println(p);
        System.out.println(s);
    }

    @Override
    protected void printInterface() {
        printCurrentPlayList();
        printPlayingTrack(PlayListManager.getInstance().getCurrentPlayList().getCurrentIndex());
    }

    @Override
    protected void printTheOptionsMenu() {
        System.out.println(CLIUtil.YELLOW + "Menu: | (p)lay | (pa)use | (re)sume | (s)top | (n)ext | (pr)evious | " +
                "(open) Open Search Browser | (pod) Change Pod | (q) Quit | (h) Help |\n"  + CLIUtil.RESET);
    }

    @Override
    protected boolean takeActions(String[] parseInput) {
        switch (parseInput[0]) {
            case "open" -> openSearchBrowser();
            case "p" -> playerManager.play();
            default -> {
                return false;
            }
        }
        return true;
    }

    private void openSearchBrowser() {
        super.getMainController().getCliManager().openSearchBrowser();
    }

    @Override
    protected void quit() {}
}
