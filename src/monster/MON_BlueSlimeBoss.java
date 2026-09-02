package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePannel;
import object.OBJ_BronzeCoin;
import object.OBJ_GradScroll;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Shield_Wood_Circle;
import object.OBJ_gpuTreasure;
import object.OBJ_ironDoor;

public class MON_BlueSlimeBoss extends Entity {

    GamePannel gp; // because its in a diff package
    public static final String monName = "Blue Slime Boss";

    public MON_BlueSlimeBoss(GamePannel gp) {
        super(gp);

        this.gp = gp;
        
        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;
        sleep = true;
        
        int size = gp.tileSize * 5;

        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;
        type = 2; // monster
        stunDuration = 120;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {

        int scale = 5;

        if (inRage == false) { 
            up1 = setup("/res/monster/blueSlimeBoss/p1up1.png", gp.tileSize * scale, gp.tileSize * scale);
            up2 = setup("/res/monster/blueSlimeBoss/p1up2.png", gp.tileSize * scale, gp.tileSize * scale);
            down1 = setup("/res/monster/blueSlimeBoss/p1down1.png", gp.tileSize * scale, gp.tileSize * scale);
            down2 = setup("/res/monster/blueSlimeBoss/p1down2.png", gp.tileSize * scale, gp.tileSize * scale);
            left1 = setup("/res/monster/blueSlimeBoss/p1left1.png", gp.tileSize * scale, gp.tileSize * scale);
            left2 = setup("/res/monster/blueSlimeBoss/p1left2.png", gp.tileSize * scale, gp.tileSize * scale);
            right1 = setup("/res/monster/blueSlimeBoss/p1right1.png", gp.tileSize * scale, gp.tileSize * scale);
            right2 = setup("/res/monster/blueSlimeBoss/p1right2.png", gp.tileSize * scale, gp.tileSize * scale);
        } else if (inRage) {
            up1 = setup("/res/monster/blueSlimeBoss/p2up1.png", gp.tileSize * scale, gp.tileSize * scale);
            up2 = setup("/res/monster/blueSlimeBoss/p2up2.png", gp.tileSize * scale, gp.tileSize * scale);
            down1 = setup("/res/monster/blueSlimeBoss/p2down1.png", gp.tileSize * scale, gp.tileSize * scale);
            down2 = setup("/res/monster/blueSlimeBoss/p2down2.png", gp.tileSize * scale, gp.tileSize * scale);
            left1 = setup("/res/monster/blueSlimeBoss/p2left1.png", gp.tileSize * scale, gp.tileSize * scale);
            left2 = setup("/res/monster/blueSlimeBoss/p2left2.png", gp.tileSize * scale, gp.tileSize * scale);
            right1 = setup("/res/monster/blueSlimeBoss/p2right1.png", gp.tileSize * scale, gp.tileSize * scale);
            right2 = setup("/res/monster/blueSlimeBoss/p2right2.png", gp.tileSize * scale, gp.tileSize * scale);
        }

        
    }

    public void getAttackImage() {

        int scale = 5;
        if (inRage == false) { 
            attackUp1 = setup("/res/monster/blueSlimeBoss/p1attackup1.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackUp2 = setup("/res/monster/blueSlimeBoss/p1attackup2.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackDown1 = setup("/res/monster/blueSlimeBoss/p1attackdown1.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackDown2 = setup("/res/monster/blueSlimeBoss/p1attackdown2.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackLeft1 = setup("/res/monster/blueSlimeBoss/p1attackleft1.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackLeft2 = setup("/res/monster/blueSlimeBoss/p1attackleft2.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackRight1 = setup("/res/monster/blueSlimeBoss/p1attackright1.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackRight2 = setup("/res/monster/blueSlimeBoss/p1attackright2.png", gp.tileSize*scale*2, gp.tileSize*scale);
        } else if (inRage) {
            attackUp1 = setup("/res/monster/blueSlimeBoss/p2attackup1.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackUp2 = setup("/res/monster/blueSlimeBoss/p2attackup2.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackDown1 = setup("/res/monster/blueSlimeBoss/p2attackdown1.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackDown2 = setup("/res/monster/blueSlimeBoss/p2attackdown2.png", gp.tileSize*scale, gp.tileSize*scale*2);
            attackLeft1 = setup("/res/monster/blueSlimeBoss/p2attackleft1.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackLeft2 = setup("/res/monster/blueSlimeBoss/p2attackleft2.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackRight1 = setup("/res/monster/blueSlimeBoss/p2attackright1.png", gp.tileSize*scale*2, gp.tileSize*scale);
            attackRight2 = setup("/res/monster/blueSlimeBoss/p2attackright2.png", gp.tileSize*scale*2, gp.tileSize*scale);
        }
        
    }
    public void setDialogue() {
        dialogues[0][0] = "oi";
        dialogues[0][1] = "you've got to help me";
        dialogues[0][2] = "this slime on my hea-";

    }
    public void setAction() {

        if(inRage == false && life < maxLife/2) {
            inRage = true;
            getImage();
            getAttackImage();
            // enhance 
            defaultSpeed++;
            speed = defaultSpeed;
            attack += 2;
        }
        
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);

        }
        else {
            getRandomDirection(120);
        }

        if (attacking == false) {
            // check if it attacks
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize*5);
        }
        
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {

        gp.bossBattleOn = false;
        Progress.blueSlimeBossDefeated = true;

        gp.stopMusic();
        gp.playMusic(22);

        // remove the iron door.
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_ironDoor.objName)){
                gp.playSE(26);
                gp.obj[gp.currentMap][i] = null;
            }
        }

        // set the monster drop
        dropItem(new OBJ_gpuTreasure(gp));
        Progress.gpuObtained = true;
    }
}
