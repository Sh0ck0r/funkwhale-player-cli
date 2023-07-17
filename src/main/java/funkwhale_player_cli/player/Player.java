package funkwhale_player_cli.player;

import com.goxr3plus.streamplayer.stream.StreamPlayerException;

import java.io.IOException;

public abstract class Player {

    public Player() {

    }

    public abstract void play(String remoteAudioFile) throws StreamPlayerException, IOException;

    public abstract void pause();

    public abstract boolean isPlaying();

    public abstract void resume();

    public abstract void stop();
}
