package funkwhale_player_cli.player;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import funkwhale_player_cli.funkwhale.FunkwhaleManager;
import funkwhale_player_cli.playlist.PlayListManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerManager {

    private static volatile PlayerManager instance;

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

    private PlayerManager() {}

    public void play() {
        String remoteAudioFile = FunkwhaleManager.getInstance().getPodURL() +
                PlayListManager.getInstance().getCurrentPlayList().playCurrentTrack();

        try {
            InputStream fileInputStream = new URL(remoteAudioFile).openStream();
            Player player = new Player(fileInputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
