package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Key extends Entity {

    GamePannel gp;

    public OBJ_Key(GamePannel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = "Key";
        down1 = setup("/res/objects/key.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA key innit brav.";
        price = 350;
        stackable = true;

    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        boolean used = false;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            gp.ui.currentDialogue = "You used the key to open the door.";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            used = true;
        } else {
            gp.ui.currentDialogue = "There is no door nearby.";
        }

        return used;
    }
    
}
