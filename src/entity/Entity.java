package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePannel;
import main.UtitlityTool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Entity {
    GamePannel gp;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 45, 45);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    public Entity attacker;
    
 
    // state
    public int worldX, worldY;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean collisionOn = false;
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;

    // counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;


    // attributes
    public ArrayList<Entity> inventory = new ArrayList<>();
    public int defaultSpeed;
    public final int maxInventorySize = 20;
    public String direction = "down";
    public int speed;
    public String name;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int defense;
    public int agility;
    public int attack;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    // Item attributes
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // type
    public int type; // 0 = player, 1 = npc, 2 = monster, 3 = item
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_gradScroll = 7;
    public final int type_pickupOnly = 8;
    public final int type_obstacle = 9;
    public final int type_light = 10;

    public Entity(GamePannel gp) {
        this.gp = gp;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return worldY + solidArea.y;
    }
    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol() {
        return worldX/gp.tileSize;
    }
    public int getRow() {

        return worldY/gp.tileSize;
    }
    public int getXdistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYdistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void setAction() {
        //to be overridden
    }
    public void damageReaction() {
        //to be overridden
    }
    public void speak() {
       
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

        if (dialogues[dialogueIndex] == null) { // check here, since index updates PREVIOUSLY!
            dialogueIndex = 0;
            return;
        }
    }
    public void interact() {

    }
    public boolean use(Entity entity) {
        return false;
    }
    public void checkDrop() {
        //to be overridden
    }
    public void dropItem(Entity droppedItem) {


        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor() {
        return new Color(65, 50, 30);
    }
    public int getParticleSize() {
        return 6;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }
    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            damagePlayer(attack);
        }

    }
    public void update() {

        if (knockBack) {
            checkCollision();
            if (collisionOn == true) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }

            else if (collisionOn == false) {
                switch (knockBackDirection) {
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }

            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }

        }
        else if (attacking == true) {
            attacking();
        }
        else {
            setAction();
            checkCollision();

            if (collisionOn == false) {
                switch (direction) {
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) { // adjust incinvincibility duration here
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (offBalance == true) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }

    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        // straight, horizontal defines the hitbox where the monster can attack 

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch(direction) {
            case "up":
                if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }

        if (targetInRange) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }

    }
    public void checkShootOrNot(int rate, int shotInterval) {
        // check if it shoots a projectile
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter >= shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            for (int j = 0; j < gp.projectileList[1].length; j++) {
                if (gp.projectileList[gp.currentMap][j] == null) {
                    gp.projectileList[gp.currentMap][j] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }   
    }
    public void getRandomDirection() {

        // get a random direction if not on path..
            actionLockCounter++;
        if (actionLockCounter == 120) {
            
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from
            if (i <= 25) {
                direction = "up";
            }
            else if (i > 25 && i <= 50) {
                direction = "down";
            }
            else if (i > 50 && i <= 75) {
                direction = "left";
            }
            else if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up": oppositeDirection = "down"; break;
            case "down": oppositeDirection = "up"; break;
            case "left": oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // save current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWith = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for attackArea
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this) == true ) {
                    damagePlayer(attack);
                }
            }
            else { // player
                // Check monster collision with the updated solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower, direction);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectileList);
                gp.player.damageProjectile(projectileIndex);
            }
            


            // After checking collision, restore original worldX, worldY, and solidArea
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWith;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack) {

        if (gp.player.invincible == false) {

            int damage = attack - gp.player.defense;

            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {

                // parry 
                if (gp.player.guardCounter < 10) {
                    damage = 0;
                    gp.playSE(19);
                    setKnockBack(this, gp.player, knockBackPower, direction);
                    offBalance = true;
                    spriteCounter -= 60; // return back to prev sprite..
                }
                // guard
                else {
                    damage /= 3;
                    gp.playSE(20);
                }
            }
            else {
                // no guard 
                gp.playSE(6);
                if (damage < 1) {
                    damage = 1;
                }
            }

            if (damage != 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower, direction);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower, String direction) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public void draw (Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // only draw object when it is in the screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch(direction) {
                case "up":
                    if (attacking == true) {
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) {image = attackUp1;}
                        if (spriteNum == 2) {image = attackUp2;}
                    }
                    else {
                        if (spriteNum == 1) {image = up1;}
                        if (spriteNum == 2) {image = up2;}
                    }
                    break;
                case "down":
                    if (attacking == true) {
                        if (spriteNum == 1) {image = attackDown1;}
                        if (spriteNum == 2) {image = attackDown2;}
                    }
                    else {
                        if (spriteNum == 1) {image = down1;}
                        if (spriteNum == 2) {image = down2;}
                    }
                    break;
                case "left":
                    if (attacking == true) {
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1) {image = attackLeft1;}
                        if (spriteNum == 2) {image = attackLeft2;}
                    }
                    else {
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                    }
                    break;
                case "right":
                    if (attacking == true) {
                        if (spriteNum == 1) {image = attackRight1;}
                        if (spriteNum == 2) {image = attackRight2;}
                    }
                    else {
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
                    }
                    break;
            }

            // monster hp bar
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double)gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35 , 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize+2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)(hpBarValue), 10);

                hpBarCounter++;
                
                if (hpBarCounter > 600) { // adjust hp bar duration here
                    hpBarOn = false;
                    hpBarCounter = 0;
                }
            }


            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            
            g2.drawImage(image, tempScreenX, tempScreenY, null);

            changeAlpha(g2, 1f);

        }

    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*2) {
            changeAlpha(g2, 1f);
        } else if (dyingCounter <= i*3) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*4) {
            changeAlpha(g2, 1f);
        } else if (dyingCounter <= i*5) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*6) {
            alive = false;
        }

    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        //load a single image
        UtitlityTool uTool = new UtitlityTool();

        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public boolean haveResource(Entity user) {
        // always override 
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user) {
        //always override
    }
    public void searchPath (int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        // System.out.println(gp.pFinder.search());

        if (gp.pFinder.search() == true) {
            // next worldX and Y

            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            if(nextX>worldX+1&&direction=="right"||nextY>worldY+1&&direction=="down"||nextY<worldY+1&&direction=="up"||nextX<worldX&&direction=="left")
                {
                    //direction stays the same as it was
                }
			
			else if(nextY>worldY+1) {
				direction="down";
			}
			else if(nextY<worldY+1){
				direction="up";
			}
			else if(nextX<worldX){
				direction="left";
			}
			else if(nextX>worldX){
				direction="right";
			}
            
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;

            // if (nextCol == goalCol && nextRow == goalRow) {
            //     System.out.println("Goal REACHED!");
            //     onPath = false;
            // }

        }
    }
    public int getDetected(Entity user, Entity[][] target, String targetName) {
        int index = 999;
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up": nextWorldY = user.getTopY() - 1; break;
            case "down": nextWorldY = user.getBottomY() + 1; break;
            case "left": nextWorldX = user.getLeftX() - 1; break;
            case "right": nextWorldX = user.getRightX() + 1; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col &&
                    target[gp.currentMap][i].getRow() == row &&
                    target[gp.currentMap][i].name.equals(targetName)) {
                        index = i;
                        break;
                }
            }
        }

        return index;


    }

}
