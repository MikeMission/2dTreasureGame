package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Entity;
import entity.Player;
import interactive_tiles.InteractiveTile;
import tile.TileManager;

public class GamePannel extends javax.swing.JPanel implements Runnable {
    
    // screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // world settings
    public int maxWorldCol; // defualt 50
    public int maxWorldRow;


    // fps
    final int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this); 
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;


    // ENTITY AND OBJECT
    public Player player;
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster [] = new Entity[10];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE 
    public int gameState;
    public final int tileState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;




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
        System.out.println(screenWidth + " " + screenHeight);
        gameState = tileState;
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
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // update information 
                update();
                // draw screen with updated information
                repaint(); // == paintComponent(Graphics g)
                delta--;
                drawCount++;

            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        if (gameState == playState) {
            // PLAYER
            player.update();

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // MONSTERS
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    } else if (monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    else if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }
        }
        
        if (gameState == pauseState) {

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == tileState) {
            ui.draw(g2);
        }

        // PAUSE SCREEN


        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            // INTERACTIVE TILES
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].draw(g2);
                }
            }

            // ENTITY
            entityList.add(player);
        
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
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



        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
        music.stop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }


}
