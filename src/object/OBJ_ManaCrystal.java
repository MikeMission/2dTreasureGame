package object;

import entity.Entity;
import main.GamePannel;


public class OBJ_ManaCrystal extends Entity {
    GamePannel gp;
    
    public OBJ_ManaCrystal(GamePannel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        value = 1;
        name = "Mana Crystal";
        down1 = setup("/res/objects/manaCrystal_full.png", gp.tileSize, gp.tileSize);
        image = setup("/res/objects/manaCrystal_full.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/manaCrystal_empty.png", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {
        gp.ui.addMessage("+" + value + " MP");
        entity.mana += value;
        gp.playSE(11);
    }
}
