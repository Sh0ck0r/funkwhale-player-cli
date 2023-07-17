package funkwhale_player_cli.player;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class AudioPlayer extends Player implements StreamPlayerListener {

    private final StreamPlayer streamPlayer;
    private double volume;
    private static AudioPlayer instance;

    public static AudioPlayer getInstance() {
        AudioPlayer localInstance = instance;
        if (localInstance == null) {
            synchronized (AudioPlayer.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AudioPlayer();
                }
            }
        }
        return localInstance;
    }

    private AudioPlayer() {
        streamPlayer = new StreamPlayer();
        volume = 0.5;
    }

    @Override
    public void play(String remoteAudioFile) throws StreamPlayerException, IOException {
        BufferedInputStream streamAudio = new BufferedInputStream(new URL(remoteAudioFile).openStream());
        streamPlayer.open(streamAudio);
        streamPlayer.play();
        streamPlayer.setGain(volume);
    }

    @Override
    public void pause() {
        streamPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return !streamPlayer.isStopped();
    }

    @Override
    public void stop() {
        streamPlayer.stop();
    }

    @Override
    public void resume() {
        streamPlayer.resume();
    }

    @Override
    public void opened(Object o, Map<String, Object> map) {
        System.out.println("opened");

    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map<String, Object> map) {
        System.out.println("progress");
    }

    @Override
    public void statusUpdated(StreamPlayerEvent streamPlayerEvent) {
        System.out.println("statusUpdated");
    }
}
