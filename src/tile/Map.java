package tile;

import main.GamePannel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Map extends TileManager {
    GamePannel gp;
    BufferedImage worldMap[];
    public boolean miniMapOn = false;

    public Map(GamePannel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }

    public void createWorldMap() {
        if (mapTileNum == null) {
            System.out.println("Error: mapTileNum is null!");
            return;
        }
        
        worldMap = new BufferedImage[mapTileNum.length];
        
        for (int i = 0; i < mapTileNum.length; i++) {
            if (mapTileNum[i] == null) {
                System.out.println("Map " + i + " is null, skipping...");
                continue;
            }
            
            // Get dimensions from the actual map data
            int mapCols = mapTileNum[i].length;
            int mapRows = mapTileNum[i][0].length;
            int worldMapWidth = gp.tileSize * mapCols;
            int worldMapHeight = gp.tileSize * mapRows;
            
            System.out.println("Creating world map " + i + " size: " + mapCols + "x" + mapRows);
            
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) worldMap[i].createGraphics();

            for (int row = 0; row < mapRows; row++) {
                for (int col = 0; col < mapCols; col++) {
                    int tileNum = mapTileNum[i][col][row];
                    int x = gp.tileSize * col;
                    int y = gp.tileSize * row;

                    if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null && tile[tileNum].image != null) {
                        g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
                    } else {
                        // Draw error tile
                        g2.setColor(Color.RED);
                        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
                        g2.setColor(Color.WHITE);
                        g2.drawString("E:" + tileNum, x + 10, y + 20);
                    }
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {
        // bg
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        
        // draw world map
        int width = 500;
        int height = 500;
        int x = gp.screenWidth/2 - width/2;
        int y = gp.screenHeight/2 - height/2;

        // Get the actual map dimensions
        int currentMap = gp.currentMap;
        if (currentMap < 0 || currentMap >= worldMap.length || worldMap[currentMap] == null) {
            System.out.println("Error: World map " + currentMap + " not created!");
            return;
        }
        
        int mapCols = mapTileNum[currentMap].length;
        int mapRows = mapTileNum[currentMap][0].length;
        
        // Calculate proper aspect ratio
        double mapAspectRatio = (double) mapCols / mapRows;
        double displayAspectRatio = (double) width / height;
        
        int displayWidth = width;
        int displayHeight = height;
        
        if (mapAspectRatio > displayAspectRatio) {
            displayHeight = (int) (width / mapAspectRatio);
            y = gp.screenHeight/2 - displayHeight/2;
        } else {
            displayWidth = (int) (height * mapAspectRatio);
            x = gp.screenWidth/2 - displayWidth/2;
        }

        g2.drawImage(worldMap[currentMap], x, y, displayWidth, displayHeight, null);
        
        // Draw player
        double scale = (double)(gp.tileSize * mapCols) / displayWidth;
        int playerX = (int) (x + gp.player.worldX/scale);
        int playerY = (int) (y + gp.player.worldY/scale);
        int playerSize = (int) (gp.tileSize/scale);
        
        // Clamp player position to map bounds
        playerX = Math.max(x, Math.min(x + displayWidth - playerSize, playerX));
        playerY = Math.max(y, Math.min(y + displayHeight - playerSize, playerY));

        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

        g2.setFont(g2.getFont().deriveFont(32f));
        g2.setColor(Color.WHITE);
        g2.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(Graphics2D g2) {
        if (miniMapOn) {
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - width - 50;
            int y = 50;

            // Get actual map dimensions
            int currentMap = gp.currentMap;
            if (currentMap < 0 || currentMap >= worldMap.length || worldMap[currentMap] == null) {
                return;
            }
            
            int mapCols = mapTileNum[currentMap].length;
            int mapRows = mapTileNum[currentMap][0].length;
            
            // Calculate proper aspect ratio
            double mapAspectRatio = (double) mapCols / mapRows;
            double displayAspectRatio = (double) width / height;
            
            int displayWidth = width;
            int displayHeight = height;
            
            if (mapAspectRatio > displayAspectRatio) {
                displayHeight = (int) (width / mapAspectRatio);
                y = 50 + (height - displayHeight) / 2;
            } else {
                displayWidth = (int) (height * mapAspectRatio);
                x = gp.screenWidth - width - 50 + (width - displayWidth) / 2;
            }

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[currentMap], x, y, displayWidth, displayHeight, null);

            // Draw player
            double scale = (double)(gp.tileSize * mapCols) / displayWidth;
            int playerX = (int) (x + gp.player.worldX/scale);
            int playerY = (int) (y + gp.player.worldY/scale);
            int playerSize = (int) (gp.tileSize/3);
            
            // Clamp player position
            playerX = Math.max(x, Math.min(x + displayWidth - playerSize, playerX));
            playerY = Math.max(y, Math.min(y + displayHeight - playerSize, playerY));
            
            g2.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}