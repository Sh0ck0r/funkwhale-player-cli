package funkwhale_player_cli.playlist;

import org.json.JSONObject;

import java.io.File;

public class Track {

    private String name;
    private String artist;
    private String listenUrl;

    public Track(JSONObject trackData) {
        name = trackData.get("title").toString();
        listenUrl = trackData.get("listen_url").toString();
    }

    public String getListenUrl() {
        return listenUrl;
    }

    @Override
    public String toString() {
        return "| " + name + " |";
    }
}
