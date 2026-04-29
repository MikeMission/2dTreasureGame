package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePannel;

public class OBJ_MudBall extends Projectile {

    GamePannel gp; 

    public OBJ_MudBall(GamePannel gp) {
        super(gp);
        this.gp = gp;

        name = "Mud Ball";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() {
        up1 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/mudBall.png", gp.tileSize, gp.tileSize);
    }
    
    public boolean haveResource(Entity user) {
        boolean haveResource = false;

        if (user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {
        return new Color(65, 50, 30);
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
