package entity;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import interactive_tiles.IT_PressurePlate;
import interactive_tiles.InteractiveTile;
import main.GamePannel;
import object.OBJ_ironDoor;

public class NPC_Boulder extends Entity {

    public static final String npcName = "Boulder";
    
    public NPC_Boulder(GamePannel gp) {
        super(gp);

        name = npcName;
        direction = "down";
        speed = 4;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 44;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;

        dialogueSet = -1;


        getImage();
        setDialogue();

    }

     public void getImage() {

        up1 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/boulder.png", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0][0] = "whats up";

    }
    public void setAction() {
    }

    

    public void update() {
        // override cuz it moves because npc.
    }

    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null){
            dialogueSet = 0;
        }
        // onPath = true; // toggle to make npc follow.
    }

    public void move(String playerDirection) {
        this.direction = playerDirection;

        checkCollision();

        if (collisionOn == false) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        detectPlate();
    }   

    public void detectPlate() {

        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> boulderList = new ArrayList<>();

        // create a plate list
        for (int i = 0; i < gp.iTile[1].length; i++) {

            if (gp.iTile[gp.currentMap][i] != null && 
                gp.iTile[gp.currentMap][i].name != null &&
                gp.iTile[gp.currentMap][i].name.equals(IT_PressurePlate.itName)) {
                    plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        for (int i = 0; i < gp.npc[1].length; i++) {

            if (gp.npc[gp.currentMap][i] != null && 
                gp.npc[gp.currentMap][i].name.equals(NPC_Boulder.npcName)) {
                    boulderList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;
        // check the plate list 

        for (int plate = 0; plate < plateList.size(); plate++) {
            int xDistance = Math.abs(worldX - plateList.get(plate).worldX);
            int yDistance = Math.abs(worldY - plateList.get(plate).worldY);
            int distance = Math.max(xDistance,yDistance);

            if (distance < 8) {
                if (LinkedEntity == null) { 
                    LinkedEntity = plateList.get(plate);
                    gp.playSE(3);
                }
            }
            else if (LinkedEntity == plateList.get(plate)) {
                // unlink
                LinkedEntity = null;
            }

        }
        for (int boulder = 0; boulder < boulderList.size(); boulder++) {
            // count the boulders on the plate
            if (boulderList.get(boulder).LinkedEntity != null) {
                count++;
            }
        }
        // all the boulders are on the plates
        if (count == boulderList.size()) {
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_ironDoor.objName)) {
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSE(26);
                }
            }
        }
        
    }
}