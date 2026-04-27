package interactive_tiles;

import entity.Entity;
import main.GamePannel;

public class InteractiveTile extends Entity{

    GamePannel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePannel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrect = false;
        return isCorrect;
    }

    public void playSE() {
        //to be overridden
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void update() {
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 25) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
