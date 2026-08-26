package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_HealthPotion extends Entity {

    GamePannel gp;
    int value = 5;

    public OBJ_HealthPotion(GamePannel gp) {
        super(gp);
        
        this.gp = gp;

        type = type_consumable;
        name = "Health Potion";
        down1 = setup("/res/objects/healthPotion.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRestores " + value + " HP.";
        price = 200;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used " + name + " -> +" + value + "HP.";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }

}