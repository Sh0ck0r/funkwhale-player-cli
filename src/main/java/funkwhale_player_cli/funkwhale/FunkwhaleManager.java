package funkwhale_player_cli.funkwhale;

import funkwhale_player_cli.cli.entity.AuthenticationScreen;
import funkwhale_player_cli.playlist.Track;

import java.net.MalformedURLException;
import java.net.URL;

public class FunkwhaleManager {

    private static volatile FunkwhaleManager instance;
    private String podURL;
    private FunkwhaleAPI funkwhaleAPI;

    public static FunkwhaleManager getInstance() {
        FunkwhaleManager localInstance = instance;
        if (localInstance == null) {
            synchronized (FunkwhaleManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FunkwhaleManager();
                }
            }
        }
        return localInstance;
    }

    private FunkwhaleManager() {
        funkwhaleAPI = FunkwhaleAPI.getInstance();
    }

    public String getPodURL() {
        return podURL;
    }

    public void setPodURL(String podURL) {
        if (!podURL.startsWith("http://") && !podURL.startsWith("https://")) {
            this.podURL = "https://" + podURL;
        } else {
            this.podURL = podURL;
        }

        System.out.println(this.podURL + " is set up as your Funkwhale Pod URL.");
    }

    public Track getTrackById(String trackId) {
        String reqUrl = podURL + "/api/v1/tracks/" + trackId;
        System.out.println(reqUrl);
        return new Track(
                FunkwhaleHelper.formatStringifyResponseToJson(funkwhaleAPI.get(reqUrl))
        );
    }
}
