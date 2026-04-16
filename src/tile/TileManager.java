package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;



import main.GamePannel;
import main.UtitlityTool;

public class TileManager {
    
    GamePannel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePannel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/res/map/map02TileData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
                
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        // Initialize tile array based on fileNames size
        tile = new Tile[fileNames.size()];
        getTileImage();

        // get the maxWorldCol and maxWorldRow from map text file
        is = getClass().getResourceAsStream("/res/map/map02.txt");
        br = new BufferedReader(new InputStreamReader(is));
        try {
            String line2 = br.readLine();
            String[] maxTile = line2.split(" ");

            gp.maxWorldRow = maxTile.length;
            gp.maxWorldCol = maxTile.length;
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadMap("/res/map/map02.txt");
    }

    public void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {

            String imagePath = "/res/tile/" + fileNames.get(i);
            boolean collision; 

            if (collisionStatus.get(i).equals("true")) {
                collision = true;
            } else {
                collision = false;
            }


            setup(i, imagePath, collision);
        }
    }


    public void setup(int index, String imagePath, boolean collision) {

        //setup tile image and collision
    
        UtitlityTool uTool = new UtitlityTool();


        try {
            tile[index] = new Tile();
            tile[index].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadMap(String filePath) {
        //load map from text file

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g2) {

        int WorldCol = 0;
        int worldRow = 0;


        while (WorldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[WorldCol][worldRow];

            int worldX = WorldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            // the worldX + gp.tileSize is for rendering tiles + a tileSize off screen
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            WorldCol++;

            if (WorldCol == gp.maxWorldCol) {
                WorldCol = 0;
                worldRow++;

            }
        }
    }

}
