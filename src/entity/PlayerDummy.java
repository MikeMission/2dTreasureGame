package entity;

import main.GamePannel;

public class PlayerDummy extends Entity  {

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePannel gp) {
        super(gp);

        name = npcName;
        getImage();
    }

    public void getImage() {
    //load player image
        up1 = setup("/res/player/plrUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/plrUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/plrDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/plrDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/plrLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/plrLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/plrRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/plrRight2.png", gp.tileSize, gp.tileSize);
    }
}