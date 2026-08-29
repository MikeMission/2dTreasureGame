package object;

import entity.Entity;
import main.GamePannel;

public class OBJ_Tent extends Entity{

    GamePannel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/objects/tent.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRestores your health \nand mana when \nyou sleep in it.";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        
        gp.gameState = gp.sleepState;
        gp.playSE(18);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;

    }
}
