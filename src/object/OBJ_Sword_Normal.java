package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_Sword_Normal extends Entity{
    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal (GamePannel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/res/objects/swordNormal.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nA temp sword.\nAttack +1";
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
