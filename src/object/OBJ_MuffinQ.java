package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_MuffinQ extends Entity {

    GamePannel gp;
    int value = 10;
    public static final String objName = "Quest";

    public OBJ_MuffinQ(GamePannel gp) {
        super(gp);
        
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/muffin.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nYou save some for your\nneighbours.";
        price = 10;
        stackable = false;
    }

    // don't use this, instead just use when speaking to neighbour npc or somethn

    // public boolean use(Entity entity) {

    //     boolean used = false;

    //     int objIndex = getDetected(entity, gp.obj, "Door"); // instead

    //     if (objIndex != 999) {
    //         startDialogue(this, 0);
    //         gp.playSE(3);
    //         gp.obj[gp.currentMap][objIndex] = null;
    //         used = true;
    //     } else {
    //         startDialogue(this, 1);
    //     }

    //     return used;
    // }

}