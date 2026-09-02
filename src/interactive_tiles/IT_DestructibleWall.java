package interactive_tiles;

import java.awt.Color;
import entity.Entity;
import main.GamePannel;

public class IT_DestructibleWall extends InteractiveTile {

    GamePannel gp;

    public IT_DestructibleWall(GamePannel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "Dry Tree";
        down1 = setup("/res/interactiveTiles/destructibleWall.png", gp.tileSize, gp.tileSize);
        image = setup("/res/interactiveTiles/destructibleWall.png", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 5;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrect = false;

        if (entity.currentWeapon.type == type_pickaxe) {
            isCorrect = true;
        }

        return isCorrect;
    }

    public void playSE() {
        gp.playSE(25);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }
    
    public Color getParticleColor() {
        return new Color(85,47,30);
    }
    public int getParticleSize() {
        return 11;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }

}