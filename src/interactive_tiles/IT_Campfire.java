package interactive_tiles;

import main.GamePannel;

public class IT_Campfire extends InteractiveTile {

    GamePannel gp;

    public IT_Campfire(GamePannel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        name = "Campfire";
        down1 = setup("/res/interactiveTiles/campfire.png", gp.tileSize, gp.tileSize);
        image = setup("/res/interactiveTiles/campfire.png", gp.tileSize, gp.tileSize);
        destructible = true;
            
    }
}