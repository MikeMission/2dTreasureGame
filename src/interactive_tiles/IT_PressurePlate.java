package interactive_tiles;

import main.GamePannel;

public class IT_PressurePlate extends InteractiveTile {

    GamePannel gp;
    public static final String itName = "pressure plate";

    public IT_PressurePlate(GamePannel gp, int col, int row) {
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


        name = itName;
        down1 = setup("/res/interactiveTiles/pressurePlate.png", gp.tileSize, gp.tileSize);
        image = setup("/res/interactiveTiles/pressurePlate.png", gp.tileSize, gp.tileSize);
        destructible = true;
            
    }
}