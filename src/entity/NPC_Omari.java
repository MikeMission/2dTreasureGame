package entity;


import main.GamePannel;


public class NPC_Omari extends Entity {
    
    public NPC_Omari(GamePannel gp) {
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

        dialogueSet = -1;

        getImage();
        setDialogue();
        // setItems();
    }

     public void getImage() {

        up1 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/omari/down1.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0][0] = "...";
        dialogues[0][1] = "I have been waiting for you, comp grad.";
        dialogues[0][2] = "Here is a gift.";
        dialogues[0][3] = "Use it wisely, for you will be facing\n something greater than I.";

        dialogues[1][0] = "What are you still doing here?";

        dialogues[2][0] = "Stay any longer and you will be paying my rent.";

        dialogues[3][0] = "I'm hungry.";

    }

    // public void setItems() {

    //     inventory.add(new OBJ_HealthPotion(gp));
    //     inventory.add(new OBJ_Boots(gp));
    //     inventory.add(new OBJ_Key(gp));
    //     inventory.add(new OBJ_Axe(gp));

    // }

    // limiting movement 

    // public void setAction() {
    //     // NPC action code here

    //     actionLockCounter++;
    //     if (actionLockCounter == 120) {
            
    //         Random random = new Random();
    //         int i = random.nextInt(100)+1; // pick up a number from
    //         if (i <= 50) {
    //             direction = "up";
    //         }
    //         else if (i > 50) {
    //             direction = "down";
    //         }

    //         actionLockCounter = 0;
    //     }

    // }

    @Override
    public void speak() {
        direction = "down";
        startDialogue(this, dialogueSet);
        
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null){
            dialogueSet = 1;
        }

        
    }


}