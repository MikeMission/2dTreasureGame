package monster;

import java.util.Random;

import entity.Entity;
import main.GamePannel;
import object.OBJ_BronzeCoin;
import object.OBJ_GradScroll;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_MudBall;
import object.OBJ_Shield_Wood_Circle;

public class MON_BlueSlime extends Entity {

    GamePannel gp; // because its in a diff package

    public MON_BlueSlime(GamePannel gp) {
        super(gp);

        this.gp = gp;
        
        type = type_monster;
        name = "Blue Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_MudBall(gp);
 
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
    
    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;
        
        if (onPath == false && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1;
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 7) {
            onPath = false;
        }

    }

    public void setAction() {
            // NPC action code here

        if (onPath == true) {

            // int goalCol = 46;
            // int goalRow = 5;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

            int i = new Random().nextInt(200) + 1;
            if (i > 197 && projectile.alive == false && shotAvailableCounter >= 30) {
                projectile.set(worldX, worldY, direction, true, this);

                for (int j = 0; j < gp.projectileList[1].length; j++) {
                    if (gp.projectileList[gp.currentMap][j] == null) {
                        gp.projectileList[gp.currentMap][j] = projectile;
                        break;
                    }
                }

                shotAvailableCounter = 0;
            }

        } else {
            actionLockCounter++;
            if (actionLockCounter == 120) {
                
                Random random = new Random();
                int i = random.nextInt(100)+1; // pick up a number from
                if (i <= 25) {
                    direction = "up";
                }
                else if (i > 25 && i <= 50) {
                    direction = "down";
                }
                else if (i > 50 && i <= 75) {
                    direction = "left";
                }
                else if (i > 75 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }

        }

        
    }

    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;

        onPath = true;
    }

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
