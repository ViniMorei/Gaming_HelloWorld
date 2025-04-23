package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[10];

    public Sound() {
        soundURL[0] = getClass().getResource("/sfx/background_fixed.wav");
        soundURL[1] = getClass().getResource("/sfx/meow.wav");
        soundURL[2] = getClass().getResource("/sfx/explosion.wav");
        soundURL[3] = getClass().getResource("/sfx/snow.wav");
        soundURL[4] = getClass().getResource("/sfx/zap.wav");
        soundURL[5] = getClass().getResource("/sfx/pick-up.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void play() {
        clip.start();
    }


    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void stop() {
        clip.stop();
    }
}
