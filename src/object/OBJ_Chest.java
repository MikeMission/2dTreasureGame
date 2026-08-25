package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Chest extends Entity {

    GamePannel gp;

    public OBJ_Chest(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Chest";
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

    public void setLoot(Entity loot) {
        this.loot = loot;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;

        if (!opened) {
            gp.playSE(16);

            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and find: " + loot.name + "!");

            if (gp.player.canObtainItem(loot) == false) {
                sb.append("\nBut your inventory is full, so you leave it there.");
            } else {
                sb.append("\nYou put the " + loot.name + " in your inventory.");
                down1 = image2;
                opened = true;

            }
            gp.ui.currentDialogue = sb.toString();
            
        } else {
            gp.ui.currentDialogue = "The chest is empty.";
            // gp.playSE(17);
            // errr close chest doesn't really make sense once looted.
        }
    }
    
}
