package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Lamp extends Entity {

    GamePannel gp;

    public static final String objName = "Lamp";

    public OBJ_Lamp(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/lamp.png", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 32; // should cover around ~2 tiles
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "";

    }

    public void interact() {

        startDialogue(this, 2);
        // gp.playSE(17);
        // errr close chest doesn't really make sense once looted.

    }
    
}
