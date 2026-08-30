package interactive_tiles;

import java.awt.Color;
import java.util.Random;

import entity.Entity;
import main.GamePannel;
import object.OBJ_BronzeCoin;
import object.OBJ_GradScroll;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Shield_Wood_Circle;

public class IT_IronOre extends InteractiveTile {

    GamePannel gp;

    public IT_IronOre(GamePannel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "Dry Tree";
        down1 = setup("/res/interactiveTiles/destructibleWall.png", gp.tileSize, gp.tileSize); // change the sprite later..
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
        gp.playSE(12);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }
    
    public Color getParticleColor() {
        return new Color(65, 65, 65);
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

    // loot, drop ore...
    public void checkDrop() {
        // roll dice
        int i = new Random().nextInt(100) + 1;

        // set the monster drop
        if (i < 50) {
            dropItem(new OBJ_BronzeCoin(gp));
        } else if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        } else if (i >= 75 && i < 85) {
            dropItem(new OBJ_ManaCrystal(gp));
        } else if (i >= 85 && i < 95) {
            dropItem(new OBJ_Shield_Wood_Circle(gp));
        } else if (i >= 95 && i <= 100) {
            dropItem(new OBJ_GradScroll(gp));
        }
        
    }
}