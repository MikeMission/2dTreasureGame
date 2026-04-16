package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePannel gp) {

        super(gp);

        name = "Chest";
        down1 = setup("/res/objects/chest.png", gp.tileSize, gp.tileSize);

    }
    
}
