package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import object.OBJ_Sword_Normal;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;

import main.GamePannel;
import main.KeyHandler;



public class Player extends Entity {
    
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    int standCounter = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePannel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // attackArea.width = 36;
        // attackArea.height = 36;


        setDefualtValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefualtValues() {
        worldX = gp.tileSize * 20 - (gp.tileSize / 2);
        worldY = gp.tileSize * 20 - (gp.tileSize / 2);
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        agility = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defense = getDefense();
        name = "comp grad"; // prob change this to actual player

    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return agility * currentShield.defenseValue;
    }


    public void getPlayerImage() {
        //load player image

        up1 = setup("/res/player/plrUp1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/plrUp2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/plrDown1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/plrDown2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/plrLeft1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/plrLeft2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/plrRight1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/plrRight2.png", gp.tileSize, gp.tileSize);


    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == type_sword) {
            attackUp2 = setup("/res/player/plr_sword_up2.png", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/plr_sword_down2.png", gp.tileSize, gp.tileSize*2);
            attackLeft2 = setup("/res/player/plr_sword_left2.png", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/plr_sword_right2.png", gp.tileSize*2, gp.tileSize);
        }
        else if (currentWeapon.type == type_axe) {
            attackUp2 = setup("/res/player/plr_axe_up2.png", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/plr_axe_down2.png", gp.tileSize, gp.tileSize*2);
            attackLeft2 = setup("/res/player/plr_axe_left2.png", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/plr_axe_right2.png", gp.tileSize*2, gp.tileSize);
        } else if (currentWeapon.type == type_gradScroll) {
            // default attack image with scroll..
            attackUp2 = setup("/res/player/plr_attack_up2.png", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/plr_attack_down2.png", gp.tileSize, gp.tileSize*2);
            attackLeft2 = setup("/res/player/plr_attack_left2.png", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/plr_attack_right2.png", gp.tileSize*2, gp.tileSize);
        }

        // default attack images (unarmed)
        attackUp1 = setup("/res/player/plr_attack_up1.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/plr_attack_down1.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/plr_attack_left1.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/player/plr_attack_right1.png", gp.tileSize*2, gp.tileSize);
    }

    public void update() {
        
        if (attacking == true) {
            attacking();
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            // which key is pressed


            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }


            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);    
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            if (collisionOn == false && keyH.enterPressed == false) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if (keyH.enterPressed == true && attackCanceled == false) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            
            if (!attacking) { // prevent overloading the spriteCounter when attacking
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
        }

        else {
            standCounter ++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        // INVINCIBILITY
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
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

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
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

            // Check monster collision with the updated solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, restore original worldX, worldY, and solidArea
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWith;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {

            String text;

            if (inventory.size() < maxInventorySize) {
                inventory.add(gp.obj[index]);
                gp.playSE(1);
                text = "You picked up " + inventory.get(inventory.size()-1).name + "!";
            }
            else {
                text = "You cannot carry any more items!";
            }
            gp.ui.addMessage(text);
            gp.obj[index] = null;

        }
    }

    public void interactNPC(int index) {

        if (gp.keyH.enterPressed == true) {

            if (index != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
                
            }
        }

    }

    public void damageMonster(int index) {
        if (index != 999) {
            if (gp.monster[index].invincible == false) {
                
                gp.playSE(5);

                int damage = attack - gp.monster[index].defense;
                if(damage < 0) {damage = 0;}

                gp.monster[index].life -= damage;
                gp.ui.addMessage(damage + " damage");
                gp.monster[index].invincible = true;
                gp.monster[index].damageReaction();
            
                if (gp.monster[index].life <= 0) {
                    gp.monster[index].dying = true;
                    exp += gp.monster[index].exp;
                    gp.ui.addMessage(gp.monster[index].name + " was slain by " + name);
                    gp.ui.addMessage("+" + gp.monster[index].exp + " exp");
                    checkLevelUp();

                }
            }
        }
    }

    public void checkLevelUp(){

        if (exp >= nextLevelExp) {
            int prevLevel = level;
            level ++;
            nextLevelExp = nextLevelExp*3;
            maxLife += 2;
            strength ++;
            agility ++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(4);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Leveled up " + prevLevel + " -> " + level;
        }

    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_gradScroll) {
                currentWeapon = selectedItem;
                attack = getAttack();
                gp.playSE(9);
                gp.ui.addMessage("Equipped " + currentWeapon.name);
                getPlayerAttackImage();
            }
            else if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
                gp.playSE(9);
                gp.ui.addMessage("Equipped " + currentShield.name);
                getPlayerAttackImage();
            }
            else if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }

    }

    public void contactMonster(int index) {
        if (index != 999) {
            // damage player
            
            if (invincible == false) {
                gp.playSE(6);
                
                int damage = gp.monster[index].attack - defense;
                if(damage < 0) {damage = 0;}

                life -= damage;
                invincible = true;
            }

        }
    }

}
