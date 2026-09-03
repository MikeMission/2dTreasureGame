package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.PlayerDummy;
import monster.MON_BlueSlimeBoss;
import object.OBJ_gpuTreasure;
import object.OBJ_ironDoor;

public class CutsceneManager {
    GamePannel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y; 
    String endCredit;

    // scene numbers
    public final int NA = 0;
    public final int blueSlimeBoss = 1;
    public final int gpuObtained = 2;
    public final int teleport = 3;
    public final int ending = 4; // implement later lol
    public final int beggining = 5; // implement later lol

    // \n
    public CutsceneManager (GamePannel gp) {
        this.gp = gp;
        endCredit = "Program/Music/Art\n" 
                + "Mike Mission"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Special Thanks\n"
                + "Ryi Snow\n"
                + "Ate\n"
                + "Jasp\n"
                + "Ma\n"
                + "Da\n"
                + "Daniel\n"
                + "Nathan B\n"
                + "Charlie\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "My Favourites\n"
                + "Silence Suzuka\n"
                + "Nice Nature\n" 
                + "Tokai Teio\n"
                + "Machikanitannhauser\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Its 31st august, as I'm writing this\n"
                + "1:40am, it seems I've made the deadline\n"
                + "While listening to:\n"
                + "Daniel Ceaser's Freudian\n"
                + "Tame impala's The Slow Rush\n"
                + "Laura Shigihara's PVS OST\n"
                + "Pink Floyd's Dark Side Of The Moon 2001 Remaster\n"
                + "Colplay's Parachutes\n"
                + "Ichika Nito's Metaphor\n"
                + "Muse's Drones\n"
                + "Kali Uchis' ORQUIDEAS\n"
                + "Franz Ferndinad's Franz Ferndinad\n"
                + "\n\n\n\n\n\n\n\n\n\n\n"
                + "From the bottom of my 1l gym bottle\n"
                + "Thank you for playing my game.";


    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch(sceneNum) {
            case beggining: scene_beggining();break;
            case blueSlimeBoss: scene_blueSlimeBoss(); break;
            case gpuObtained: scene_gpuObtained(); break;
            case teleport: scene_Transition(); break;
            case ending: scene_ending();break;

        }
    }
    // CORE SCENES
    public void scene_beggining() {
        if (scenePhase == 0) {
            drawBlackBackground(1f);
            
            drawString(1f, 70, gp.screenHeight/2, "Your tale starts here", 40);
            if (counterReached(200)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 1) {
            phaseOut();
        }
        if (scenePhase == 2) {
            // phaseOut();

            try {
                BufferedImage titleImage = ImageIO.read(getClass().getResourceAsStream("/res/menuBackgrounds/beginningCutsceneBgs/graduation.png"));
                g2.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String text = "It's the day you've been grinding for"
            + "\n 4 years of your life for this degree";
            drawString(1f, 28, 500, text, 28);
            if (counterReached(600)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 3) {
            phaseOut();
        }
        if (scenePhase == 4) {
            try {
                BufferedImage titleImage = ImageIO.read(getClass().getResourceAsStream("/res/menuBackgrounds/beginningCutsceneBgs/train.png"));
                g2.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String text = "you go back home, thinking about"
            + "\n those 4 years you've spent"
            + "\n just slaving away at learning.";

            drawString(1f, 28, 500, text, 28);
            if (counterReached(600)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            try {
                BufferedImage titleImage = ImageIO.read(getClass().getResourceAsStream("/res/menuBackgrounds/beginningCutsceneBgs/house.png"));
                g2.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String text = "you finally arrive home"
            + "\nbut it feels a bit different."
            + "\n there's monsters lurking everywhere";

            drawString(1f, 28, 340, text, 28);
            if (counterReached(600)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 6) {
            drawBlackBackground(1f);
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }

            String text = "nothing a computer science grad couldn't handle";

            drawString(alpha, 40, 250, text, 70);
            if (counterReached(150)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 7) {
            phaseOut();
        }
        if (scenePhase == 8) {
            // start the game.
            gp.gameState = gp.playState;
            scenePhase = NA;
            sceneNum = 0;
            // gp.playMusic(22);
            // play home music
        }
        
    }

    public void scene_ending() {
        if (scenePhase == 0) {
            drawBlackBackground(1f);

            drawString(1f, 70, gp.screenHeight/2, "The Tale Of a Comp Grad", 40);
            if (counterReached(300)) {
                counter = 0;
                // gp.playMusic(0); ending song
                scenePhase++;
            }

        }
        if (scenePhase == 1) {
            // endcredits

            drawBlackBackground(1f);
            y = gp.screenHeight/2;

            drawString(1f, 38, y, endCredit,40);
            if (counterReached(300)) {
                counter = 0;
                // gp.playMusic(0); ending lol
                scenePhase++;
            }
        }
        if (scenePhase == 2) {
            drawBlackBackground(1f);
            // scrolling the endcredit string.
            y--;
            drawString(1f, 38f, y, endCredit, 40);
        }
    }

    public void scene_Transition() {
        if (scenePhase == 0) {
            phaseIn();
        }
        if (scenePhase == 1) {
            // change map.
            if (counterReached(1)) { // do this for 1 frame..
                counter = 0;
                gp.currentMap = gp.eHandler.tempMap;
                gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
                gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
                gp.eHandler.previousEventX = gp.player.worldX;
                gp.eHandler.previousEventY = gp.player.worldY;
                gp.changeArea();
                scenePhase++;

            }
        }
        if (scenePhase == 2) {
            phaseOut();
        }

        if (scenePhase == 3) {
            // return back to playState.

            scenePhase = NA;
            sceneNum = 0;
            gp.gameState = gp.playState;
        }

    }
    
    // BOSS SCENES & PROGRESSION
    public void scene_blueSlimeBoss() {
        if (scenePhase == 0) {
            gp.bossBattleOn = true;

            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_ironDoor(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*26;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*20;
                    gp.obj[gp.currentMap][i].temp = true; 
                    gp.playSE(26);
                    break;
                }
            }
            // replacing the plr with a dummy.

            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp); 
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }


            gp.player.drawing = false;

            scenePhase++;
        }
        if (scenePhase == 1) {

            // move camera to the boss
            gp.player.worldY += 2;

            if (gp.player.worldY > gp.tileSize * 32) {
                scenePhase++;
            }
        }
        if (scenePhase == 2) {
            // wake the boss

            for (int i = 0; i < gp.monster[1].length; i++) {
                if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MON_BlueSlimeBoss.monName) {
                    
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i]; 
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {

            // boss speak
            gp.ui.drawDialogueScreen();

        }
        if (scenePhase == 4) {
            // return to plr

            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    // restore plr pos
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }

            gp.player.drawing = true;
            scenePhase = NA;
            sceneNum = 0;
            gp.gameState = gp.playState;
            
            gp.stopMusic();
            gp.playMusic(23);
        }
    }

    public void scene_gpuObtained() {

        if (scenePhase == 0) {
            gp.stopMusic();
            gp.ui.npc = new OBJ_gpuTreasure(gp);
            scenePhase++;
        }
        if (scenePhase == 1) {
            // display dialogues

            gp.ui.drawDialogueScreen();

        }
        if (scenePhase == 2) {
            // play fanfare
            gp.playSE(4);
            scenePhase++;
        }
        if (scenePhase == 3) {

            if (counterReached(150)) {
                counter = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            phaseIn();
        }
        if (scenePhase == 5) {

            drawBlackBackground(1f);
            
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }

            String text = "After fighting the blue slime infected man,"
            + "\n he woke up and thanked you for saving him."
            + "\n He gave you this gpu."
            + "\n You remember, when you were young,"
            + "\n you've always wanted to make your own pc.";

            drawString(alpha, 38f, 150, text, 70);
            if (counterReached(700)) {
                counter = 0;
                scenePhase++;
            }

        }
        if (scenePhase == 6) {
            phaseIn();
        }
        if (scenePhase == 7) {
            phaseOut();
        }
        if (scenePhase == 8) {
            // return back to playstate
            gp.gameState = gp.playState;
            scenePhase = NA;
            sceneNum = 0;
            gp.playMusic(22);
        }
    }


    // TOOLS
    public void phaseIn() {
        // phase in
        alpha += 0.05f;
        if (alpha > 1f) {
            alpha = 1f;
            scenePhase++;
        }
        drawBlackBackground(alpha);
    }

    public void phaseOut() {
        alpha -= 0.05f;
        if (alpha < 0f) {
            alpha = 0f;
            scenePhase++;
        }
        drawBlackBackground(alpha);
    }
    
    public boolean counterReached(int duration) {
        boolean counterReached = false;
        counter++;
        if (counter > duration) {
            counterReached = true;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);  
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);  
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")) {
            
            int x = gp.ui.getXforCenteredText(line);

            g2.setFont(g2.getFont().deriveFont(fontSize));
            g2.setColor(Color.BLACK);
            g2.drawString(line, x+3, y+3);

            g2.setFont(g2.getFont().deriveFont(fontSize));
            g2.setColor(Color.WHITE);
            g2.drawString(line, x, y);

            y += lineHeight;
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }
}