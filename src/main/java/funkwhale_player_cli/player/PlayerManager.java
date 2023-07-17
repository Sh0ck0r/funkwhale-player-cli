package funkwhale_player_cli.player;

import funkwhale_player_cli.funkwhale.FunkwhaleManager;
import funkwhale_player_cli.playlist.PlayListManager;


public class PlayerManager {

    private static volatile PlayerManager instance;
    private Player currentPlayer;
    private boolean paused;


    public static PlayerManager getInstance() {
        PlayerManager localInstance = instance;
        if (localInstance == null) {
            synchronized (PlayerManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlayerManager();
                }
            }
        }
        return localInstance;
    }

    private PlayerManager() {
        currentPlayer = AudioPlayer.getInstance();
    }

    public void play() {
        String remoteAudioFile = FunkwhaleManager.getInstance().getPodURL() +
                PlayListManager.getInstance().getCurrentPlayList().playCurrentTrack();

        new Thread(() -> {
            try {
                currentPlayer.play(remoteAudioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void pause() {
        if (currentPlayer.isPlaying()) {
            currentPlayer.pause();
        }
        paused = false;
    }

    public void resume() {
        if (currentPlayer.isPlaying()) {
            currentPlayer.resume();
        }
        paused = true;
    }

    public void next() {
        if (currentPlayer.isPlaying()) {
            currentPlayer.stop();
        }

        PlayListManager.getInstance().getCurrentPlayList().next();
        if (!isPaused()) {
            play();
        }
    }

    public boolean isPaused () {
        return paused;
    }
}
