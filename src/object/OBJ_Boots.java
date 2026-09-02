package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Boots extends Entity {
    GamePannel gp;

    public static final String objName = "Boots";

    public OBJ_Boots(GamePannel gp) {

        super(gp);
        this.gp = gp;
        name = objName;
        type = type_consumable;
        down1 = setup("/res/objects/boots.png", gp.tileSize, gp.tileSize);
        value = 1;
        description = "[" + name + "]\nA pair of old boots.\nAgility +1";
        price = 500;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used the " + name + ".\nYour agility has increased by " + value + ".";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        entity.agility += value;
        gp.playSE(2);
        return true;
    }
    
}
