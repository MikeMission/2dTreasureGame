package main;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_BronzeCoin;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_GradScroll;
import object.OBJ_HealthPotion;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_MudBall;
import object.OBJ_Pickaxe;
import object.OBJ_Shield_Wood;
import object.OBJ_Shield_Wood_Circle;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_Throwing_Knife;
import object.OBJ_ironDoor;

public class EntityGenerator {
    GamePannel gp;

    public EntityGenerator(GamePannel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        
        Entity obj = null;
        // ================================================
        // REMEMBER TO ADD ALL NEW OBJECTS
        // ================================================
        switch(itemName) {
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
            case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
            case OBJ_HealthPotion.objName: obj = new OBJ_HealthPotion(gp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
            case OBJ_Shield_Wood_Circle.objName: obj = new OBJ_Shield_Wood_Circle(gp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
            case OBJ_GradScroll.objName: obj = new OBJ_GradScroll(gp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
            case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_BronzeCoin.objName: obj = new OBJ_BronzeCoin(gp); break;
            case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
            case OBJ_MudBall.objName: obj = new OBJ_MudBall(gp); break;
            case OBJ_Throwing_Knife.objName: obj = new OBJ_Throwing_Knife(gp); break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
            case OBJ_ironDoor.objName: obj = new OBJ_ironDoor(gp); break;

        }
        return obj;
    }

}
