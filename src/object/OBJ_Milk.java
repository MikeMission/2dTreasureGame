package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Milk extends Entity {

    GamePannel gp;
    int value = 10;
    public static final String objName = "Milk";

    public OBJ_Milk(GamePannel gp) {
        super(gp);
        
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/milk.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHigh in iron!\nRestores " + value + " HP.";
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