package funkwhale_player_cli.playlist;

import org.json.JSONObject;

public class Track {

    private String name;
    private String artist;
    private String listenUrl;

    public Track(JSONObject trackData) {
        name = trackData.get("title").toString();
        artist = trackData.getJSONObject("artist").get("name").toString();
        listenUrl = trackData.get("listen_url").toString();
    }

    public String getListenUrl() {
        return listenUrl;
    }

    @Override
    public String toString() {
        return "| " + artist + " - " + name + " |";
    }
}
