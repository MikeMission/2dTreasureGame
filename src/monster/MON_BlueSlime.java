package monster;

import entity.Entity;
import main.GamePannel;

public class MON_BlueSlime extends Entity {

    GamePannel gp; // because its in a diff package

    public MON_BlueSlime(GamePannel gp) {
        super(gp);

        this.gp = gp;
        
        name = "Blue Slime";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
 
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = 2; // monster

        getImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/blue_slime_down_1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/blue_slime_down_2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/blue_slime_down_1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/blue_slime_down_2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/blue_slime_down_1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/blue_slime_down_2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/blue_slime_down_1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/blue_slime_down_2.png", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            int i = new java.util.Random().nextInt(100) + 1; // pick a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
