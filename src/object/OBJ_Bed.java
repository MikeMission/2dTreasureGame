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
        solidArea.width = 45;
        solidArea.height = 60; // should cover around ~2 tiles
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "You rest.";
        dialogues[0][1] = "Your health has been replenished Your progress have been saved.";
    }

    
    public void interact() {
        gp.gameState = gp.sleepState;
        gp.playSE(18);
        gp.player.life = gp.player.maxLife;
        gp.player.getSleepingImage(down1);
        gp.saveLoad.save();
    }

}
