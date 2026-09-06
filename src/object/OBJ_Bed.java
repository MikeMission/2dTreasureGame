package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Bed extends Entity {

    GamePannel gp;

    public static final String objName = "Bed";

    public OBJ_Bed(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/bedframe1.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/bedframe2.png", gp.tileSize, gp.tileSize);
        down1 = image;
        down2 = image2;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 60; // should cover around ~2 tiles
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
