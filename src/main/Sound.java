package main;

import java.net.URL;

import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/GreenField.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/achievement.wav");
        soundURL[5] = getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/res/sound/recievedamage.wav");
        soundURL[7] = getClass().getResource("/res/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/res/sound/slotSound.wav");
        soundURL[9] = getClass().getResource("/res/sound/equipSound.wav");
        soundURL[10] = getClass().getResource("/res/sound/fireball.wav");
        soundURL[11] = getClass().getResource("/res/sound/manaRestore.wav");
        soundURL[12] = getClass().getResource("/res/sound/cuttree.wav");
    }


    public void setFile(int i) {
        try {
            javax.sound.sampled.AudioInputStream ais = javax.sound.sampled.AudioSystem.getAudioInputStream(soundURL[i]);
            clip = javax.sound.sampled.AudioSystem.getClip();
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
        