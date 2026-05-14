package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Boots extends Entity {
    GamePannel gp;
    public OBJ_Boots(GamePannel gp) {

        super(gp);
        this.gp = gp;
        name = "Boots";
        down1 = setup("/res/objects/boots.png", gp.tileSize, gp.tileSize);
        value = 1;
        description = "[" + name + "]\nA pair of old boots.\nAgility +1";
        price = 20;
    }

    public void use(Entity entity) {
        gp.ui.addMessage("+1 Agility");
        entity.agility += value;
        gp.playSE(2);
    }
    
}
