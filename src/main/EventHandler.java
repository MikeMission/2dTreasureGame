package main;

import entity.Entity;

public class EventHandler {
    GamePannel gp;
    EventRect eventRect[][][];
    int eventRectDefaultX, eventRectDefaultY;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePannel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {


            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map ++;
                }

            }

        }

    }

    public void checkEvent() {

        // check if the player character is more than one tile away from the previous event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if (hit(0,26, 21, "down") == true) {damagePit(gp.dialogueState);}
            else if (hit(0,26, 24, "any") == true) {damagePit(gp.dialogueState);}

            else if (hit(0,18,21, "any") == true) {healingPool(gp.dialogueState);}

            else if (hit(0, 16, 16, "up") == true) {teleport(1, 24, 27);}
            else if (hit (1, 24, 28, "down") == true) {teleport(0, 16, 16);}

            else if (hit (1, 33, 22, "up")== true) {speak(gp.npc[1][1]);}
        } 
    
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;


                }
                
            }

            // after checking, restore original position

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }

        return hit;

    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!\nYou lose 1 life.";
        gp.player.life -= 1;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) { 
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You rest a while\nYour life and mana have been restored.\n Your progress have been saved.";
            gp.player.attackCanceled = true;
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.player.ammo = 10;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
            
        }
    }

    public void teleport(int map, int col, int row) {

        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;

        gp.playSE(3);
        
    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

}
