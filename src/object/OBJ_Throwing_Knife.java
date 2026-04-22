package object;

import entity.Projectile;
import main.GamePannel;

// implement later

public class OBJ_Throwing_Knife extends Projectile {

    GamePannel gp; // because its in a diff package
    
     public OBJ_Throwing_Knife(GamePannel gp) {
        super(gp);
        this.gp = gp;

        name = "Throwing Knife";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        attack = 2;

    }

   


}
