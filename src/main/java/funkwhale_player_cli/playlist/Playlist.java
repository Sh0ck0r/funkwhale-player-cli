package funkwhale_player_cli.playlist;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private int namePrefix;
    private int currentIndex;
    private List<Track> items;

    protected Playlist(String name, int namePrefix) {
        this.name = name;
        this.namePrefix = namePrefix;
        currentIndex = 0;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCurrentIndex() {
        if (!items.isEmpty()) {
            return currentIndex;
        }
        return -1;
    }

    public Track getItem(int index) {
        return items.get(index);
    }

    public String playCurrentTrack() {
        return items.get(currentIndex).getListenUrl();
    }

    public void add(Track track) {
        items.add(track);
    }

    public void print() {
        if (!items.isEmpty()) {
            printPlayList();
        } else {
            System.out.println("Current Playlist is Empty.");
        }
    }

    private void printPlayList() {
        for (Track track : items) {
            System.out.println(track);
        }
    }
}
