package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Heart extends Entity {

    GamePannel gp;

    public OBJ_Heart(GamePannel gp) {
        super(gp);
        this.gp = gp;
        name = "Heart";
        type = type_pickupOnly;
        value = 2;
        down1 = setup("/res/objects/fullHeart.png", gp.tileSize, gp.tileSize);
        image = setup("/res/objects/fullHeart.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/halfHeart.png", gp.tileSize, gp.tileSize);
        image3 = setup("/res/objects/emptyHeart.png", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {
        gp.ui.addMessage("+" + value + " HP");
        entity.life += value;
        gp.playSE(2);
    }
}
