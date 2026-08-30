package main;

import java.awt.Graphics2D;

import entity.PlayerDummy;
import monster.MON_BlueSlimeBoss;
import object.OBJ_ironDoor;

public class CutsceneManager {
    GamePannel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    public final int NA = 0;
    public final int blueSlimeBoss = 1;

    public CutsceneManager (GamePannel gp) {
        this.gp = gp;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch(sceneNum) {
            case blueSlimeBoss: scene_blueSlimeBoss(); break;
        }
    }
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
}