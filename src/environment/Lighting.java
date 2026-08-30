package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import main.GamePannel;

public class Lighting {
    GamePannel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    // day states
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;


    public Lighting(GamePannel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        // create a buffered image
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
        

        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0,0,0,0.97f));
        }
        else {
            // Get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;
                
            // Create a gradation effect
            Color color[] = new Color[12];
            float fraction[] = new float[12];

            
            color = new Color[12];
            fraction = new float[12];

            float[] alphaValues = {0.1f, 0.42f, 0.52f, 0.61f, 0.69f, 0.76f, 0.82f, 0.87f, 0.91f, 0.91f, 0.93f, 0.94f};
            float[] fractionValues = {0f, 0.4f, 0.5f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f};

            for (int i = 0; i < color.length; i++) {
                color[i] = new Color(0, 0, 0, alphaValues[i]);
                fraction[i] = fractionValues[i];
            }
            // paint settings
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius / 2, fraction, color);

            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }
    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
    }
    public void update() {
        int duration = 4200;

        if (gp.player.lightUpdated) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        if (dayState == day) {
            
            dayCounter++;

            if (dayCounter > duration) { // 10 secs
                dayState = dusk;
                dayCounter = 0;
            }

        }

        if (dayState == dusk) {
            filterAlpha += 0.001f;

            if (filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }

        if (dayState == night) {

            dayCounter++;

            if (dayCounter > duration) {
                dayState = dawn;
                dayCounter = 0;
            }
        }

        if (dayState == dawn) {
            filterAlpha -= 0.001f;

            if (filterAlpha < 0f) {
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }
    public void draw(Graphics2D g2) {
        if (gp.currentArea == gp.outside) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }
        if (gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
            g2.drawImage(darknessFilter, 0, 0, null);
        }
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        String state = "";

        switch (dayState) {
            case day: state = "Day"; break;
            case dusk: state = "Dusk"; break;
            case night: state = "Night"; break;
            case dawn: state = "Dawn"; break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        g2.drawString(state, 800, 500);

    }
}
