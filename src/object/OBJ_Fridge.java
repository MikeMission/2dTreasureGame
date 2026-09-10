package object;

import main.GamePannel;
import entity.Entity;

import java.util.Random;

public class OBJ_Fridge extends Entity {

    GamePannel gp;

    public static final String objName = "Fridge";
    int openingCounter = 0;

    public OBJ_Fridge(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/fridge.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/fridge.png", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // randomly set loot every time initialised?
        for (int lootAmount = 0; lootAmount < 3; lootAmount++){ 
            int randomLoot = new Random().nextInt(5) + 1;
            switch (randomLoot) {
                case 1: setLoot(new OBJ_HealthPotion(gp)); break;
                case 2: setLoot(new OBJ_Grapes(gp)); break;
                case 3: setLoot(new OBJ_CinnamonRoll(gp)); break;
                case 4: setLoot(new OBJ_Milk(gp)); break;
                case 5: setLoot(new OBJ_Toast(gp)); break;
            }
        }
    }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "You opened the fridge and find:\n " + loot.name + "!" + "\nYou put the " + loot.name + "\n in your inventory.";
        dialogues[1][0] = "You've reached the daily limit";

    }

    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void interact() {
        if (openingCounter == 4) {
            opened = true;
        }

        if (!opened && openingCounter < 3) {
            gp.playSE(16);

            if (gp.player.canObtainItem(loot) == false) {
                startDialogue(this, 1);
            } else {
                startDialogue(this, 0);
                openingCounter++;
            }
            
        } 
        if (opened) {
            startDialogue(this, 1);
        }
    }
    
}
