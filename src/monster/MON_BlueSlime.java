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

    public void setAction() {
        
        if (onPath) {

            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 7, 100);

            // search the direction to go
            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));

            // check if it shoots a projectile
            checkShootOrNot(200, 7);
        }
        else {
            // check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            
            // get random direction
            getRandomDirection(120);
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
