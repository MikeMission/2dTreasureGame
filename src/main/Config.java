package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    GamePannel gp;

    public Config(GamePannel gp) {
        this.gp = gp;
    }
    
    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // full screen
            if (gp.fullScreenOn) {
                bw.write("On");
            }
            else if (!gp.fullScreenOn) {
                bw.write("Off");
            }

            bw.newLine();

            // music vol
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();
            
            // SFX vol
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            if (s.equals("On")) {
                gp.fullScreenOn = true;
            } else if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // Music Vol
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
