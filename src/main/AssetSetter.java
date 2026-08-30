package main;

public class AssetSetter {

    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }
    // FOR SOME REASON, IF YOU ADD AN OBJECT BEFORE A LOAD, IT WON'T LOAD THAT OBJECT UNTIL U PRESS NEW GAME...
    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new object.OBJ_BronzeCoin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Shield_Wood_Circle(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_GradScroll(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_HealthPotion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 46;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 31;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_HealthPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 8;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 32;
        gp.obj[mapNum][i].worldY = gp.tileSize * 2;
        i++;

        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 11;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
        i++;

        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_HealthPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 8;
        gp.obj[mapNum][i].worldY = gp.tileSize * 22;
        i++;

        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_HealthPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 42;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new object.OBJ_HealthPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 17;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;

        gp.obj[mapNum][i] = new object.OBJ_ironDoor(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 39;
        i++;
    } 

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new entity.NPC_CapKid(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 19;

        i++;
        mapNum = 1;
        gp.npc[mapNum][i] = new entity.NPC_GreenGuy(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 33;
        gp.npc[mapNum][i].worldY = gp.tileSize * 20;

        i++;
        mapNum = 2;
        gp.npc[mapNum][i] = new entity.NPC_Boulder(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 16;
        gp.npc[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        gp.npc[mapNum][i] = new entity.NPC_Boulder(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 38;
        gp.npc[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        gp.npc[mapNum][i] = new entity.NPC_Boulder(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 29;
        gp.npc[mapNum][i].worldY = gp.tileSize * 25;


    }

    public void setMonster() {
        int mapNum = 0;

        int i = 0;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 19;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new monster.MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new monster.MON_KnifeMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        mapNum = 2;

        i = 0;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;

        mapNum = 3;
        i = 0;

        gp.monster[mapNum][i] = new monster.MON_BlueSlimeBoss(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;
    }

    public void setInteractiveTile() {
        int mapNum = 0;

        int i = 0;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DryTree(gp, 19, 13);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DryTree(gp, 20, 13);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_Campfire(gp, 18, 21);i++;

        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 13, 17);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 13, 18);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 13, 19);i++;

        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 28, 19);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 29, 19);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 30, 19);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 31, 19);i++;

        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 38, 17);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 39, 17);i++;

        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 27, 30);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 28, 30);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 29, 30);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 30, 30);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 31, 30);i++;

        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 36, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 35, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 34, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 33, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 32, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 31, 37);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 31, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 31, 39);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 37, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 36, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 35, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 34, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 33, 38);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_DestructibleWall(gp, 32, 38);i++;


        gp.iTile[mapNum][i] = new interactive_tiles.IT_PressurePlate(gp,5, 22);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_PressurePlate(gp,43, 33);i++;
        gp.iTile[mapNum][i] = new interactive_tiles.IT_PressurePlate(gp,24, 34);i++;


















    }

}