package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_StrawberryCupcake extends Entity {

    GamePannel gp;
    int value = 100;
    public static final String objName = "Strawberry Cupcake";

    public OBJ_StrawberryCupcake(GamePannel gp) {
        super(gp);
        
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/strawberryCupcake.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA blessing from omari\nRestores " + value + " HP.";
        price = 200;
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