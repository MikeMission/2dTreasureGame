package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import interactive_tiles.InteractiveTile;
import tile.TileManager;

public class GamePannel extends javax.swing.JPanel implements Runnable {
    
    // screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // world settings
    public int maxWorldCol; // defualt 50
    public int maxWorldRow;
    public final int maxMap = 10; // max 10
    public int currentMap = 0;


    // fps
    final int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this); 
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    public EnvironmentManager envManager = new EnvironmentManager(this);
    Thread gameThread;


    // ENTITY AND OBJECT
    public Player player;
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster [][] = new Entity[maxMap][10];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    // public ArrayList<Entity> projectileList = new ArrayList<>();
    public Entity projectileList[][] = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE 
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;




    public GamePannel() {

        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(new java.awt.Color(77, 152, 187));
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
        player = new Player(this, keyH);
    }


    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        envManager.setup();
        System.out.println(screenWidth + " " + screenHeight);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if (fullScreenOn) {setFullScreen();}
    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
    }
    public void restart() {
        player.setDefualtValues();
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        player.setItems();
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setInteractiveTile();

    }
    public void setFullScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenWidth2 = (int) width;
		screenHeight2 = (int) height;
        //offset factor to be used by mouse listener or mouse motion listener if you are using cursor in your game. Multiply your e.getX()e.getY() by this.
		// fullScreenOffsetFactor = (float) screenWidth / (float) screenWidth2;
    }
  

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.requestFocusInWindow();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // update information 
                update();
                // draw screen with updated information
                drawToScreen();
                drawToTempScreen();

                delta--;

            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                timer = 0;
            }

        }

    }

    public void update() {

        if (gameState == playState) {
            // PLAYER
            player.update();

            // NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            // MONSTERS
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    } else if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList[1].length; i++) {
                if (projectileList[currentMap][i] != null) {
                    if (projectileList[currentMap][i].alive == true) {
                        projectileList[currentMap][i].update();
                    }
                    else if (projectileList[currentMap][i].alive == false) {
                        projectileList[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    else if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            envManager.update();
        }
        
        if (gameState == pauseState) {

        }

    }

    public void drawToTempScreen() {

        // DEBUG
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }
        

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            // INTERACTIVE TILES
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // ENTITY
            entityList.add(player);
        
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            for (int i = 0; i < projectileList[1].length; i++) {
                if (projectileList[currentMap][i] != null) {
                    entityList.add(projectileList[currentMap][i]);
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {   @Override
            
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // CLEAR ENTITY LIST
            entityList.clear();

            //ENVIRONMENT 
            envManager.draw(g2);


            // UI
            ui.draw(g2);
        }


        // DEBUG
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.setColor(Color.WHITE);

            g2.drawString("WorldX: " + player.worldX, x,y); y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x,y); y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x,y); y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x,y); y += lineHeight;
            g2.drawString("Draw Time: " + passed, x,y); y += lineHeight;
            g2.drawString("Invincible: " + player.invincibleCounter, x,y); y += lineHeight;

        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }


}
