package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_Shield_Wood extends Entity{
    public OBJ_Shield_Wood (GamePannel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/res/objects/shieldWood.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA wooden shield.\nDefense +1";

    }
}
