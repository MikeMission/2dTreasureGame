package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Grapes extends Entity {

    GamePannel gp;
    int value = 5;
    public static final String objName = "Grapes";

    public OBJ_Grapes(GamePannel gp) {
        super(gp);
        
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/grapes.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nmakes you feel healthy\nRestores " + value + " HP.";
        price = 10;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used " + name + " -> +" + value + "HP.";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }

}