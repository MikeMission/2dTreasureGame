package monster;

import java.util.Random;

import entity.Entity;
import main.GamePannel;
import object.OBJ_BronzeCoin;
import object.OBJ_GradScroll;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Shield_Wood_Circle;

public class MON_KnifeMonster extends Entity {

    GamePannel gp; // because its in a diff package

    public MON_KnifeMonster(GamePannel gp) {
        super(gp);

        this.gp = gp;
        
        type = type_monster;
        name = "Knife Monster";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 1;
        exp = 10;
        knockBackPower = 5;
        stunDuration = 80;
 
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;
        type = 2; // monster

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/knifeMonster/knifeMonsterUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/knifeMonster/knifeMonsterUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/knifeMonster/knifeMonsterDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/knifeMonster/knifeMonsterDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/knifeMonster/knifeMonsterLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/knifeMonster/knifeMonsterLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/knifeMonster/knifeMonsterRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/knifeMonster/knifeMonsterRight2.png", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("/res/monster/knifeMonster/attackUp1.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/monster/knifeMonster/attackUp2.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/monster/knifeMonster/attackdown1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/monster/knifeMonster/attackdown2.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/monster/knifeMonster/attackLeft1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/monster/knifeMonster/attackLeft2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/monster/knifeMonster/attackRight1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/monster/knifeMonster/attackRight2.png", gp.tileSize*2, gp.tileSize);
    }

    public void setAction() {
        
        if (onPath) {

            // check if it stops chasing
            checkStopChasingOrNot(gp.player, 7, 100);

            // search the direction to go
            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));
        }
        else {
            // check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            
            // get random direction
            getRandomDirection(120);
        }

        if (attacking == false) {
            // check if it attacks
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
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
