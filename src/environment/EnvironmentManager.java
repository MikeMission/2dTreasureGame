package environment;

import java.awt.Graphics2D;

import main.GamePannel;

public class EnvironmentManager {
    
    GamePannel gp;
    Lighting lighting;

    public EnvironmentManager(GamePannel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting (gp, 350);
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
    
}
