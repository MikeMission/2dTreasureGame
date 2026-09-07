package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Campfire extends Entity {

    GamePannel gp;

    public static final String objName = "Campfire";

    public OBJ_Campfire(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_lightEmmiter;
        name = objName;
        image = setup("/res/objects/campfire.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/campfire.png", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        lightRadius = 300;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 10;
        solidArea.height = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
        }

    public void setDialogue() {
        /**TODO:
         * dialogue should auto adjust based on the name of the loot.
         * if char > asdklasjd
        */
        dialogues[0][0] = "You rest a while\nYour life and mana have been restored.\n Your progress have been saved.";
        dialogues[0][1] = "That was nice.";
        dialogues[0][2] = "Monsters have respawned.";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.player.attackCanceled = true;
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.ammo = 10;
        gp.aSetter.setMonster();
        gp.saveLoad.save();
    }
    
}
