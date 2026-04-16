package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePannel gp) {

        super(gp);

        name = "Boots";
        down1 = setup("/res/objects/boots.png", gp.tileSize, gp.tileSize);
        

    }
    
}
