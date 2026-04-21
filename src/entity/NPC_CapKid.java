package entity;


import java.util.Random;


import main.GamePannel;

public class NPC_CapKid extends Entity {
    
    public NPC_CapKid(GamePannel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;


        getImage();
        setDialogue();

    }

     public void getImage() {

        up1 = setup("/res/NPC/CapKidUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/CapKidUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/CapKidDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/CapKidDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/CapKidLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/CapKidLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/CapKidRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/CapKidRight2.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0] = "So 'ooos' there then?";
        dialogues[1] = "So you've come to this island to find\n the treasure?";
        dialogues[2] = "I have been here for a while.\n Be careful of the monsters!";
    }
    public void setAction() {
        // NPC action code here

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

    public void speak() {
        super.speak();
    }


}