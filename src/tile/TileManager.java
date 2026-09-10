package tile;

import java.awt.Color;
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
    public int mapTileNum[][][];
    
    // Store dimensions for each map
    private int[] mapCols;
    private int[] mapRows;
    
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();
    boolean drawPath = false;
    
    String[] mapFilePaths = {
            "/res/map/map02.txt", // 0
            "/res/map/interior0.txt", // 1
            "/res/map/dungeonLayer1.txt", // 2
            "/res/map/dungeonLayer2.txt", // 3
            "/res/map/AteInterior.txt", // 4
            "/res/map/tutorial.txt", // 5
            "/res/map/map01.txt", // 6 100x100
            "/res/map/playerHouse.txt", // 7
            "/res/map/neighbourInterior.txt" // 8
        };

    public TileManager(GamePannel gp) {
        this.gp = gp;

        // Initialize dimension arrays
        mapCols = new int[mapFilePaths.length];
        mapRows = new int[mapFilePaths.length];

        // Load tile data
        InputStream is = getClass().getResourceAsStream("/res/map/TileDataDefault.txt");
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

        // Initialize mapTileNum as array of 2D arrays (supports different sizes)
        mapTileNum = new int[mapFilePaths.length][][];
        
        // Load all maps
        for (int map = 0; map < mapFilePaths.length; map++) {
            loadMap(mapFilePaths[map], map);
        }
        
        // Set initial map dimensions in gp
        if (mapTileNum[0] != null) {
            gp.maxWorldCol = mapCols[0];
            gp.maxWorldRow = mapRows[0];
        }
    }

    public void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {
            String imagePath = "/res/tile/" + fileNames.get(i);
            boolean collision = collisionStatus.get(i).equals("true");
            setup(i, imagePath, collision);
        }
    }

    public void setup(int index, String imagePath, boolean collision) {
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

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            // First, read all lines to determine dimensions
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            
            if (lines.isEmpty()) {
                System.out.println("Warning: Map file " + filePath + " is empty!");
                return;
            }
            
            // Get dimensions
            int rows = lines.size();
            int cols = lines.get(0).split(" ").length;
            
            // Store dimensions for this map
            mapCols[map] = cols;
            mapRows[map] = rows;
            
            System.out.println("Loaded map " + map + " (" + filePath + "): " + cols + "x" + rows);
            
            // Create array with correct dimensions for this specific map
            mapTileNum[map] = new int[cols][rows];
            
            // Fill the array
            for (int row = 0; row < rows; row++) {
                String[] numbers = lines.get(row).trim().split("\\s+");
                for (int col = 0; col < cols && col < numbers.length; col++) {
                    try {
                        mapTileNum[map][col][row] = Integer.parseInt(numbers[col]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing at map " + map + 
                                         ", row " + row + ", col " + col + 
                                         ": " + numbers[col]);
                        mapTileNum[map][col][row] = 0; // Default to 0
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error loading map: " + filePath);
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        // Get dimensions for the CURRENT map from stored arrays
        int currentMap = gp.currentMap;
        
        // Safety check
        if (currentMap < 0 || currentMap >= mapTileNum.length || mapTileNum[currentMap] == null) {
            System.out.println("Error: Map " + currentMap + " not loaded!");
            return;
        }
        
        int maxCol = mapCols[currentMap];
        int maxRow = mapRows[currentMap];
        
        // Update gp with current map dimensions for other systems
        gp.maxWorldCol = maxCol;
        gp.maxWorldRow = maxRow;

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < maxCol && worldRow < maxRow) {
            int tileNum = mapTileNum[currentMap][worldCol][worldRow];
            
            // Safety check for tile
            if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null || tile[tileNum].image == null) {
                worldCol++;
                if (worldCol == maxCol) {
                    worldCol = 0;
                    worldRow++;
                }
                continue;
            }

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Only render tiles that are on screen
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;
            if (worldCol == maxCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
        // Draw path if needed (unchanged)
        if (drawPath) {
            g2.setColor(new Color(255,0,0,70));
            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
    
    // Helper method to get map dimensions
    public int getMapCols(int mapIndex) {
        if (mapIndex >= 0 && mapIndex < mapCols.length) {
            return mapCols[mapIndex];
        }
        return 0;
    }
    
    public int getMapRows(int mapIndex) {
        if (mapIndex >= 0 && mapIndex < mapRows.length) {
            return mapRows[mapIndex];
        }
        return 0;
    }
}