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
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used " + name + " -> +" + value + " Agility";
        entity.speed += value;
        gp.playSE(2);
        return true;
    }
    
}
