package entity;


import java.awt.Rectangle;
import java.util.Random;


import main.GamePannel;

public class NPC_CapKid extends Entity {
    
    public NPC_CapKid(GamePannel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;


        getImage();
        setDialogue();

    }

     public void getImage() {

        up1 = setup("/res/NPC/capKid/CapKidUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/capKid/CapKidUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/capKid/CapKidDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/capKid/CapKidDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/capKid/CapKidLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/capKid/CapKidLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/capKid/CapKidRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/capKid/CapKidRight2.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0] = "So 'ooos' there then?";
        dialogues[1] = "You Wotttt m8\n Sharrarap";
        dialogues[2] = "Whats tha go'a do wi wo 'am doin 'en?\n ge' ou o' here";
    }
    public void setAction() {
        // NPC action code here

        if (onPath == true) {

            // int goalCol = 46;
            // int goalRow = 5;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;


            searchPath(goalCol,goalRow);

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

    public void speak() {
        super.speak();
        onPath = true;
    }


}