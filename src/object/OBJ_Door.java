package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Door extends Entity {



    public OBJ_Door(GamePannel gp) {
        super(gp);
        name = "Door";
        down1 = setup("/res/objects/door.png", gp.tileSize, gp.tileSize);
        collision = true;

    }
    
}
