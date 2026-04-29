package interactive_tiles;

import java.awt.Graphics2D;
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

    @Override
    public void draw (Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // only draw object when it is in the screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, null);
        }

    } 

}
