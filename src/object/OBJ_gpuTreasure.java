package object;

import entity.Entity;
import main.GamePannel;


public class OBJ_gpuTreasure extends Entity{
    GamePannel gp;
    public static final String objName = "gpu";

    public OBJ_gpuTreasure(GamePannel gp) {
        super(gp);

        this.gp = gp;
        type = type_pickupOnly;
        name = objName;
        down1 = setup("/res/objects/gpuTresure.png", gp.tileSize, gp.tileSize);
        setDialogues();
    }

    public void setDialogues() {
        dialogues[0][0] = "You found a gpu\n . . . ";
        dialogues[0][1] = "computer parts: 1/8";
        dialogues[0][2] = "You remember now..";
        dialogues[0][3] = "...";


        // COMPUTER PARTS:
        // CPU
        // GPU ^v^
        // RAM
        // SSD/HDD
        // PSU
        // Cooling
        // case
        // keeb+mouse+monitor
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.gpuObtained;

        return true;
    }
}
