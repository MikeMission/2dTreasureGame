package environment;

import java.awt.Graphics2D;

import main.GamePannel;

public class EnvironmentManager {
    
    GamePannel gp;
    Lighting lighting;

    public EnvironmentManager(GamePannel gp) {
        this.gp = gp;
    }

    public void update() {
        lighting.update();
    }
    
    public void setup() {
        lighting = new Lighting (gp);
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
    
}
