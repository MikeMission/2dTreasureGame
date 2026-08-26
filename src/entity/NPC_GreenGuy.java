package entity;


import java.util.Random;


import main.GamePannel;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_HealthPotion;
import object.OBJ_Key;

public class NPC_GreenGuy extends Entity {
    
    public NPC_GreenGuy(GamePannel gp) {
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
        setItems();
    }

     public void getImage() {

        up1 = setup("/res/NPC/GreenGuy/GreenGuyUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/GreenGuy/GreenGuyUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/GreenGuy/GreenGuyDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/GreenGuy/GreenGuyDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/GreenGuy/GreenGuyLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/GreenGuy/GreenGuyLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/GreenGuy/GreenGuyRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/GreenGuy/GreenGuyRight2.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0][0] = "Me slime green yeee";
        dialogues[1][0] = "Bye bye";
        dialogues[2][0] = "You cannot afford that silly.";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems() {

        inventory.add(new OBJ_HealthPotion(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe(gp));

    }

    // limiting movement 

    public void setAction() {
        // NPC action code here

        actionLockCounter++;
        if (actionLockCounter == 120) {
            
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from
            if (i <= 50) {
                direction = "up";
            }
            else if (i > 50) {
                direction = "down";
            }

            actionLockCounter = 0;
        }

    }

    @Override
    public void speak() {
        
        direction = "down";
        startDialogue(this, dialogueSet);
      
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            return;
        }
        
    }


}