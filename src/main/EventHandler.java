package main;

public class EventHandler {
    GamePannel gp;
    EventRect eventRect[][];
    int eventRectDefaultX, eventRectDefaultY;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePannel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {


            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
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
            if (hit(26, 21, "down") == true) {damagePit(26, 21, gp.dialogueState);}
            if (hit(26, 24, "any") == true) {damagePit(26, 24, gp.dialogueState);}

            if (hit(25,24, "down") == true) {healingPool(gp.dialogueState);}
            if (hit(46,7, "any") == true) {teleport(gp.dialogueState);}
        } 
    
    
    
    
    
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;


            }
            
        }

        // after checking, restore original position

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row,int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!\nYou lose 1 life.";
        gp.player.life -= 1;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) { 
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\nYour life is restored.";
            gp.player.attackCanceled = true;
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }
    }

    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.player.worldX = 34 * gp.tileSize;
        gp.player.worldY = 8 * gp.tileSize;
        gp.ui.currentDialogue = "You teleported!";
        
    }


}
