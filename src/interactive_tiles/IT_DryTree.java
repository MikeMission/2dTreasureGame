package interactive_tiles;

import java.awt.Color;
import entity.Entity;
import main.GamePannel;

public class IT_DryTree extends InteractiveTile {

    GamePannel gp;

    public IT_DryTree(GamePannel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "Dry Tree";
        down1 = setup("/res/interactiveTiles/dryTree.png", gp.tileSize, gp.tileSize);
        image = setup("/res/interactiveTiles/dryTree.png", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 5;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrect = false;

        if (entity.currentWeapon.type == type_axe) {
            isCorrect = true;
        }

        return isCorrect;
    }

    public void playSE() {
        gp.playSE(12);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
    
    public Color getParticleColor() {
        return new Color(65, 50, 30);
    }
    public int getParticleSize() {
        return 6;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }

}