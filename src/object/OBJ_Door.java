package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_Door extends Entity {

    GamePannel gp;

    public OBJ_Door(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Door";
        down1 = setup("/res/objects/door.png", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    
    public void interact() {
        gp.gameState = gp.dialogueState;

        gp.ui.currentDialogue = "You need a key to open this";
    }
}
