package data;

import java.io.ObjectOutputStream;

import entity.Entity;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileInputStream;

import main.GamePannel;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_GradScroll;
import object.OBJ_HealthPotion;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Shield_Wood;
import object.OBJ_Shield_Wood_Circle;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class SaveLoad {
    
    GamePannel gp;

    public SaveLoad(GamePannel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
        return itemName.equals("Axe") ? new OBJ_Axe(gp) :
               itemName.equals("Boots") ? new OBJ_Boots(gp) :
               itemName.equals("Grad Scroll") ? new OBJ_GradScroll(gp) :
               itemName.equals("Health Potion") ? new OBJ_HealthPotion(gp) :
               itemName.equals("Key") ? new OBJ_Key(gp) :
               itemName.equals("Lantern") ? new OBJ_Lantern(gp) :
               itemName.equals("Tent") ? new OBJ_Tent(gp) :
               itemName.equals("Normal Sword") ? new OBJ_Sword_Normal(gp) :
               itemName.equals("Wooden Circle Shield") ? new OBJ_Shield_Wood_Circle(gp) :
               itemName.equals("Wood Shield") ? new OBJ_Shield_Wood(gp) :
               itemName.equals("Door") ? new OBJ_Door(gp) :
               itemName.equals("Chest") ? new OBJ_Chest(gp) :


               null;
    }
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();
            
            // stats
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.agility = gp.player.agility;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }

            // equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // objects on the map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                
                for (int i = 0; i < gp.obj[1].length; i++) {
                    
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "N/A";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }

                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;

                    }
                }
            }

            // write 
            oos.writeObject(ds);

        }
        catch(Exception e) {
            System.out.println("Save Exception!");
            System.out.println("failed to save data to save.dat");
        }

    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.agility = ds.agility;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            
            // plr inv
            gp.player.inventory.clear();

            for(int i = 0 ; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            // equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // objects on the map

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("N/A")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];

                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].setLoot(getObject(ds.mapObjectLootNames[mapNum][i]));
                        }

                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Load Exception!");
            System.out.println("failed to load data from save.dat");
        }
    }
}
