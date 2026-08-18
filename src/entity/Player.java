package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import object.OBJ_Sword_Normal;
import object.OBJ_fireball;
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
    public boolean lightUpdated = false;

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
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }

    public void setDefualtValues() {
        worldX = gp.tileSize * 20 - (gp.tileSize / 2);
        worldY = gp.tileSize * 20 - (gp.tileSize / 2);
        direction = "down";
        defaultSpeed = 4;
        speed = defaultSpeed;

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        defense = 1;
        agility = 4; // Attack speed?? Not sure what to do w this var.
        exp = 0;
        nextLevelExp = 5;
        coin = 1000; // TEST
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        // projectile = new OBJ_MudBall(gp);
        projectile = new OBJ_fireball(gp);
        attack = getAttack();
        defense = getDefense();
        name = "comp grad"; // prob change this to actual player

    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 20 - (gp.tileSize / 2);
        worldY = gp.tileSize * 20 - (gp.tileSize / 2);
        direction = "down";
    }
    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
        transparent = false;
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense * currentShield.defenseValue;
    }

    public void getImage() {
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

    public void getAttackImage() {

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

    public void getGuardImage() {
        guardUp = setup("/res/player/plr_guard_up.png", gp.tileSize, gp.tileSize);
        guardDown = setup("/res/player/plr_guard_down.png", gp.tileSize, gp.tileSize);
        guardLeft = setup("/res/player/plr_guard_left.png", gp.tileSize, gp.tileSize);
        guardRight = setup("/res/player/plr_guard_right.png", gp.tileSize, gp.tileSize);

    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void update() {

        if (knockBack) {

                        // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);    
            gp.cChecker.checkEntity(this, gp.iTile);

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
        else if (keyH.spacePressed == true) {
            guarding = true;
            guardCounter++;
        }

        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
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

            // check interactive tile collision
            gp.cChecker.checkEntity(this, gp.iTile);

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
                attacking = true;
                spriteCounter = 0;
                gp.playSE(7);
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

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
            guarding = false;
            guardCounter = 0;

        }

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false
             && shotAvailableCounter >= 30 && projectile.haveResource(this)) {
            projectile.set(worldX, worldY, direction, true, this);
            projectile.subtractResource(this);

            for (int i = 0; i < gp.projectileList[1].length; i++) {
                if (gp.projectileList[gp.currentMap][i] == null) {
                    gp.projectileList[gp.currentMap][i] = projectile;
                    break;
                }
            }

            gp.playSE(10);
            shotAvailableCounter = 0;
        }

        // INVINCIBILITY
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (life <= 0) {
            gp.stopMusic();
            gp.gameState = gp.gameOverState;
            gp.playSE(13);
            gp.ui.commandNum = -1; // reset commandNum so ENTER DOESN'T Click again..

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
                else if (guarding == true) {
                    image = guardUp;
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
                else if (guarding == true) {
                    image = guardDown;
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
                else if (guarding == true) {
                    image = guardLeft;
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
                else if (guarding == true) {
                    image = guardRight;
                }
                else {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                break;
        }

        if (transparent == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void pickUpObject(int index) {
        if (index != 999) {

            // PICKUP ONLY ITEMS (like coins)
            if (gp.obj[gp.currentMap][index].type == type_pickupOnly) {
                gp.obj[gp.currentMap][index].use(this);
                gp.obj[gp.currentMap][index] = null;
                return;
            } else if (gp.obj[gp.currentMap][index].type == type_obstacle) {
                if (keyH.enterPressed == true) {
                    gp.obj[gp.currentMap][index].interact();
                }
            }

            // INVENTORY ITEMS
            else {
                String text;

                if (canObtainItem(gp.obj[gp.currentMap][index])) {
                    gp.playSE(1);
                    text = "You picked up " + inventory.get(inventory.size()-1).name + "!";
                }
                else {
                    text = "You cannot carry any more items!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][index] = null;
            }
        }
    }

    public void interactNPC(int index) {

        if (gp.keyH.enterPressed == true) {

            if (index != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][index].speak();
            }

        }

    }

    public void damageMonster(int index, Entity attacker, int attack, int knockBackPower, String direction) {
        if (index != 999) {
            if (gp.monster[gp.currentMap][index].invincible == false) {
                
                gp.playSE(5);
                
                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][index], attacker, knockBackPower, direction);
                }
                
                if (gp.monster[gp.currentMap][index].offBalance == true) {
                    attack *= 5;
                }

                int damage = attack - gp.monster[gp.currentMap][index].defense;
                if(damage < 0) {damage = 0;}

                gp.monster[gp.currentMap][index].life -= damage;
                gp.ui.addMessage(damage + " damage");
                gp.monster[gp.currentMap][index].invincible = true;
                gp.monster[gp.currentMap][index].damageReaction();
            
                if (gp.monster[gp.currentMap][index].life <= 0) {
                    gp.monster[gp.currentMap][index].dying = true;
                    exp += gp.monster[gp.currentMap][index].exp;
                    gp.ui.addMessage(gp.monster[gp.currentMap][index].name + " was slain by " + name);
                    gp.ui.addMessage("+" + gp.monster[gp.currentMap][index].exp + " exp");
                    checkLevelUp();

                }
            }
        }
    }

    public void damageProjectile(int index) {
        if (index != 999) {
            Entity projectile = gp.projectileList[gp.currentMap][index];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void damageInteractiveTile(int index) {
        if (index != 999) {
            if (gp.iTile[gp.currentMap][index].destructible == true && 
                gp.iTile[gp.currentMap][index].isCorrectItem(this) == true && gp.iTile[gp.currentMap][index].invincible == false) {
                    
                gp.iTile[gp.currentMap][index].playSE();
                gp.iTile[gp.currentMap][index].life --;
                gp.iTile[gp.currentMap][index].invincible = true;
                
                generateParticle(gp.iTile[gp.currentMap][index],gp.iTile[gp.currentMap][index]);

                if (gp.iTile[gp.currentMap][index].life <= 0) {
                    gp.iTile[gp.currentMap][index] = gp.iTile[gp.currentMap][index].getDestroyedForm();
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
            defense++;
            agility++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(4);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Leveled up " + prevLevel + " -> " + level;
        }

    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_gradScroll) {
                currentWeapon = selectedItem;
                attack = getAttack();
                gp.playSE(9);
                gp.ui.addMessage("Equipped " + currentWeapon.name);
                getAttackImage();
            }
            else if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
                gp.playSE(9);
                gp.ui.addMessage("Equipped " + currentShield.name);
                getAttackImage();
            }
            else if (selectedItem.type == type_light) {

                if (currentLight == selectedItem) {
                    // unequip light
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                    gp.ui.addMessage("Equipped " + currentLight.name);
                    gp.playSE(9);
                }
                lightUpdated = true;
            }

            else if (selectedItem.type == type_consumable) {
                if (selectedItem.use(this) == true) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                } else {
                    gp.ui.addMessage("Cannot use that here.");
                }
            }
        }

    }

    public void contactMonster(int index) {
        if (index != 999) {
            // damage player
            if (invincible == false && gp.monster[gp.currentMap][index].dying == false) {
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][index].attack - defense;
                if(damage < 1) {damage = 1;}

                life -= damage;
                invincible = true;
                transparent = true;
            }

        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }

        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        if (item.stackable) {
            int index = searchItemInInventory(item.name);
            if (index!= 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{ 
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else {
             if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
            }
        }
        return canObtain;
    }
}
