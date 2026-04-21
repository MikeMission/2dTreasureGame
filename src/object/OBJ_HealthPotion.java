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
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used " + name + " -> +" + value + "HP.";
        entity.life += value;
        if (entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
        gp.playSE(2);
    }

}