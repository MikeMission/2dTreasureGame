package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Chest extends Entity {

    GamePannel gp;

    public static final String objName = "Chest";

    public OBJ_Chest(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/chest.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/chestOpened.png", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "You opened the chest and find:\n " + loot.name + "!" + "\nYou put the " + loot.name + "\n in your inventory.";
        dialogues[1][0] = "You opened the chest and find: " + loot.name + "!" + "\nBut your inventory is full, so you leave it there.";
        dialogues[2][0] = "The chest is empty.";

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
                startDialogue(this, 0);
                down1 = image2;
                opened = true;
            }

            
        } else {
            startDialogue(this, 2);
            // gp.playSE(17);
            // errr close chest doesn't really make sense once looted.
        }
    }
    
}
