import javax.sound.sampled.*;
import java.io.*;

public class PlaySound {
    private Clip[] clips;

    public PlaySound(int numSounds) {
        clips = new Clip[numSounds];
    }

    public void play(String filePath, int index) {
        try {
            File musicFile = new File(filePath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                clips[index] = AudioSystem.getClip();
                clips[index].open(audioInput);
                clips[index].start();
            } else {
                System.out.println("File not found: " + filePath);
            }

        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public void stop(int index) {
        if (clips[index] != null && clips[index].isRunning()) {
            clips[index].stop();
        }
    }

    public void pause(int index) {
        if (clips[index] != null && clips[index].isActive()) {
            clips[index].stop();
        }
    }

    public void resume(int index) {
        if (clips[index] != null && !clips[index].isActive()) {
            clips[index].start();
        }
    }
}
