package entity;


import java.awt.Rectangle;
import java.util.Random;


import main.GamePannel;

public class NPC_Mum extends Entity {
    
    public NPC_Mum(GamePannel gp) {
        super(gp);

        direction = "up";
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;

        dialogueSet = -1;


        getImage();
        setDialogue();

    }

     public void getImage() {

        up1 = setup("/res/NPC/mum/up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/mum/up2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/mum/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/mum/down2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/mum/left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/mum/left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/mum/right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/mum/right2.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0][0] = "Welcome back";
        dialogues[0][1] = "I've made some muffins for you in the oven";
        dialogues[0][2] = "Take some to our neighbour for me will you?";
        dialogues[0][3] = "Also, congratulations on graduating";
        dialogues[0][4] = "I remember back in my day...";

        dialogues[1][0] = "I hope he's doing well up there.";
        dialogues[1][1] = "you're still here? get a move on";

    }
    public void setAction() {
        // NPC action code here
        
        actionLockCounter++;
        if (actionLockCounter == 150) {
            
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
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null){
            dialogueSet = 1;
        }
        // onPath = true; // toggle to make npc follow.
    }


}