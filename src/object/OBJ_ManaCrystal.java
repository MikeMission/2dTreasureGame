package object;

import entity.Entity;
import main.GamePannel;


public class OBJ_ManaCrystal extends Entity {
    GamePannel gp;
    
    public OBJ_ManaCrystal(GamePannel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        type = type_consumable;
        image = setup("/res/objects/manaCrystal_full.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/manaCrystal_empty.png", gp.tileSize, gp.tileSize);
    }
}
