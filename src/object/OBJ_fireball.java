package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePannel;

public class OBJ_fireball extends Projectile {

    GamePannel gp; 

     public OBJ_fireball(GamePannel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() {
        up1 = setup("/res/projectile/fireball_up1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/fireball_up2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/fireball_down1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/fireball_down2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/fireball_left1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/fireball_left2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/fireball_right1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/fireball_right2.png", gp.tileSize, gp.tileSize);
    }
    
    public boolean haveResource(Entity user) {
        boolean haveResource = false;

        if (user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }

        public Color getParticleColor() {
        return new Color(223, 84, 15);
    }
    public int getParticleSize() {
        return 10;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }
}
