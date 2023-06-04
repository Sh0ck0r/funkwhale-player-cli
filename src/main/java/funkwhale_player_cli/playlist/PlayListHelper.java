package funkwhale_player_cli.playlist;

public class PlayListHelper {

    private PlayListHelper() {}

    public static void addToPlayList(Playlist targetPlayList, Track track) {
        targetPlayList.add(track);
        System.out.println("The Song Added To " + targetPlayList.getName() + " Playlist");
    }
}
