package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_Shield_Wood extends Entity{
    public static final String objName = "Wood Shield";

    public OBJ_Shield_Wood (GamePannel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/res/objects/shieldWood.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA wooden shield.\nDefense +1";
        price = 50;

    }
}
