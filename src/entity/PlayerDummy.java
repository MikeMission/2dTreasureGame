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
        // load player image
        up1 = setup("/res/player/compGrad/up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/compGrad/up2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/compGrad/down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/compGrad/down2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/compGrad/left2.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/compGrad/left1.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/compGrad/right2.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/compGrad/right1.png", gp.tileSize, gp.tileSize);
    }
}