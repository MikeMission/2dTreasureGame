package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Oven extends Entity {

    GamePannel gp;

    public static final String objName = "Oven";

    public OBJ_Oven(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/oven.png", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        lightRadius = 450;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 32; // should cover around ~2 tiles
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setLoot(new OBJ_Muffin(gp));
    }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "You opened the oven and find:\n " + loot.name + "!" + "\nYou put the " + loot.name + "\n in your inventory.";
        dialogues[0][1] = "You also store an extra muffin\n for your neighbours";
    }

    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void interact() {

        if (!opened) {
            gp.playSE(16);

            if (gp.player.canObtainItem(loot) == false) {
                startDialogue(this, 1);
            } else {
                gp.player.canObtainItem(new OBJ_MuffinQ(gp)); // add the quest muffin too!
                startDialogue(this, 0);
                opened = true;
            }
        }
    }
    
}
