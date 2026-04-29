package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePannel;

public class Particle extends Entity {

    Entity generator; // the entity that generated this particle
    Color color;
    int size;
    int xd;
    int yd;
    public Particle(GamePannel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);
        this.generator = generator;
        this.color = color;
        this.xd = xd;
        this.yd = yd;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        int offset = (generator.solidArea.width - size) / 2;

        life = maxLife;
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }


    @Override
    public void update() {
        life --;

        if (life < maxLife / 3) {
            yd++;
            size--;
        }

        worldX += xd * speed;
        worldY += yd * speed;

        if (life == 0) {
            alive = false;
        }
    }
    
    @Override
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size); // can use draw image instead if want
    }

}
