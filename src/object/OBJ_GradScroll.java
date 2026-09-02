package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_GradScroll extends Entity {
    public static final String objName = "Grad Scroll";

    public OBJ_GradScroll(GamePannel gp) {
        super(gp);
        
        type = type_gradScroll;
        name = objName;
        down1 = setup("/res/objects/defaultGradScroll.png", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 7;
        description = "[" + name + "]\nA computer science\n graduate scroll.\nAttack +2";
        motion1_duration = 5;
        motion2_duration = 25;
        price = 100;
    }

}
