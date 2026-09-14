package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_noShield extends Entity{
    public static final String objName = "No Shield";

    public OBJ_noShield (GamePannel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/res/objects/noShield.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nYou're Using your fists";
        // price = 30;
    }
}
