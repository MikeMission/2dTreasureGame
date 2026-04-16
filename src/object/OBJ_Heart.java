package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Heart extends Entity {


    public OBJ_Heart(GamePannel gp) {
        super(gp);
        name = "Heart";

        image = setup("/res/objects/fullHeart.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/halfHeart.png", gp.tileSize, gp.tileSize);
        image3 = setup("/res/objects/emptyHeart.png", gp.tileSize, gp.tileSize);
    }
}
