package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_GradScroll extends Entity {
    public OBJ_GradScroll(GamePannel gp) {
        super(gp);
        
        type = type_gradScroll;
        name = "Grad Scroll";
        down1 = setup("/res/objects/defaultGradScroll.png", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nA computer science\n graduate scroll.\nAttack +2";
    }

}
