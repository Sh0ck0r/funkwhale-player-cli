package funkwhale_player_cli.playlist;

import funkwhale_player_cli.cli.entity.AuthenticationScreen;

import java.util.ArrayList;

public class PlayListManager {

    private static volatile PlayListManager instance;
    private int currentPlayListIndex;
    private ArrayList<Playlist> playLists;

    public static PlayListManager getInstance() {
        PlayListManager localInstance = instance;
        if (localInstance == null) {
            synchronized (PlayListManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlayListManager();
                }
            }
        }
        return localInstance;
    }

    private PlayListManager() {
        playLists = new ArrayList<>();
        playLists.add(new Playlist("Default", 0));
        currentPlayListIndex = 0;
    }

    public Playlist getCurrentPlayList() {
        return playLists.get(currentPlayListIndex);
    }
}
