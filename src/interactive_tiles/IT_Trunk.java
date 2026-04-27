package interactive_tiles;

import main.GamePannel;

public class IT_Trunk extends InteractiveTile {

    GamePannel gp;

    public IT_Trunk(GamePannel gp, int col, int row) {
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


        name = "Trunk";
        down1 = setup("/res/interactiveTiles/trunk.png", gp.tileSize, gp.tileSize);
        image = setup("/res/interactiveTiles/trunk.png", gp.tileSize, gp.tileSize);
        destructible = true;
            
    }


    public void update() {
        // if (destructible == true) {
        //     if (life <= 0) {
        //         destroyInteractiveTile();
        //     }
        // }
    }
}