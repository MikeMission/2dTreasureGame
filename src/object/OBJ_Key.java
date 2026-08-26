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
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You used the key to open the door.";
        dialogues[1][0] = "There is no door nearby.";
    }

    public boolean use(Entity entity) {

        boolean used = false;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            used = true;
        } else {
            startDialogue(this, 1);
        }

        return used;
    }
    
}
