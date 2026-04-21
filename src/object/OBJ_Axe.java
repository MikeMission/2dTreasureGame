package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePannel gp) {
        super(gp);
        
        type = type_axe;
        name = "Axe";
        down1 = setup("/res/objects/defaultAxe.png", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 30;
        description = "[" + name + "]\nA temp axe.\nAttack +2";
    }

}
