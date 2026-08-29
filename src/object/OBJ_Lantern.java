package object;

import entity.Entity; 
import main.GamePannel;

public class OBJ_Lantern extends Entity{

    public static final String objName = "Lantern";

    public OBJ_Lantern(GamePannel gp) {
        super(gp);
        type = type_light;
        name = objName;
        down1 = setup("/res/objects/lantern.png", gp.tileSize, gp.tileSize);
        price = 200;
        lightRadius = 450;
        description = "[" + name + "]\nA lantern that emits light\nin a wide radius.";
    }


}
