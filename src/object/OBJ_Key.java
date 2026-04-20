package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePannel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/res/objects/key.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA key innit brav.";

    }
    
}
