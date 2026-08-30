package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Pickaxe extends Entity {

    public static final String objName = "PickAxe";

    public OBJ_Pickaxe(GamePannel gp) {
        super(gp);
        
        type = type_pickaxe;
        name = objName;
        down1 = setup("/res/objects/pickaxe.png", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 30;
        description = "[" + name + "]\nA tool to mine\n destructible tiles.";
        price = 100;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }

}
