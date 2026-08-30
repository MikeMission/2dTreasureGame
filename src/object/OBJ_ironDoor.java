package object;

import main.GamePannel;
import entity.Entity;

public class OBJ_ironDoor extends Entity {

    GamePannel gp;
    public static final String objName = "Iron Door";

    public OBJ_ironDoor(GamePannel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/res/objects/ironDoor.png", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "Cannot open manually";

    }
    public void interact() {
        startDialogue(this, 0);
    }
}
