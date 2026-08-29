package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Shield_Wood_Circle extends Entity {
    public static final String objName = "Wooden Circle Shield";

    public OBJ_Shield_Wood_Circle(GamePannel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/res/objects/shieldCircleWood.png", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA simple wooden shield.\nDefense +2";
    }

}
