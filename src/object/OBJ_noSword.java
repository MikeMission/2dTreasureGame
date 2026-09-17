package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_noSword extends Entity{
    public static final String objName = "No Sword";

    public OBJ_noSword (GamePannel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/res/objects/noSword.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 25;
        attackArea.height = 25;
        description = "[" + name + "]\nYou're Using your fists";
        knockBackPower = 1;
        motion1_duration = 5;
        motion2_duration = 25;
        sellable = false;
        // price = 30;
    }
}
