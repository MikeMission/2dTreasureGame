package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_BronzeCoin extends Entity {
    GamePannel gp;
    public static final String objName = "Bronze Coin";

    public OBJ_BronzeCoin(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/res/objects/bronzeCoin.png", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.ui.addMessage("+1 Bronze Coin");
        entity.coin += value;
        gp.playSE(1);
        return true;
    }
}
