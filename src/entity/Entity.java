package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    
 
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

    // counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;



    // attributes
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
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    // Item attributes
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

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

    public Entity(GamePannel gp) {
        this.gp = gp;
    }

    public void setAction() {
        //to be overridden
    }

    public void damageReaction() {
        //to be overridden
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            return;
        }
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
    }

    public void use(Entity entity) {
        //to be overridden
    }

    public void checkDrop() {
        //to be overridden
    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null) {
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX;
                gp.obj[i].worldY = worldY;
                break;
            }
        }
    }

    public void update() {
        setAction();

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

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
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

    }

    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            gp.playSE(6);

            int damage = attack - gp.player.defense;
            if(damage < 0) {damage = 0;}

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
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

            switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;} 
                else if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;} 
                else if (spriteNum == 2) {image = down2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                else if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                else if (spriteNum == 2) {image = right2;}
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
            
            g2.drawImage(image, screenX, screenY, null);

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
}

