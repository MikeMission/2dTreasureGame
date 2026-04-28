package main;

public class AssetSetter {

    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;
        gp.obj[i] = new object.OBJ_BronzeCoin(gp);
        gp.obj[i].worldX = gp.tileSize * 27;
        gp.obj[i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[i] = new object.OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 27;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[i] = new object.OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 27;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[i] = new object.OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[i] = new object.OBJ_Shield_Wood_Circle(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[i] = new object.OBJ_GradScroll(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[i] = new object.OBJ_HealthPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 19;
        
        

    } 

    public void setNPC() {
        gp.npc[0] = new entity.NPC_CapKid(gp);
        gp.npc[0].worldX = gp.tileSize * 24;
        gp.npc[0].worldY = gp.tileSize * 19;
    }

    public void setMonster() {
        int i = 0;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 17;
        gp.monster[i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 19;
        gp.monster[i].worldY = gp.tileSize * 28;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 12;
        gp.monster[i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 11;
        gp.monster[i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 10;
        gp.monster[i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 10;
        gp.monster[i].worldY = gp.tileSize * 20;
    }

    public void setInteractiveTile() {
        int i = 0;
        gp.iTile[i] = new interactive_tiles.IT_DryTree(gp, 19, 13);i++;
        gp.iTile[i] = new interactive_tiles.IT_DryTree(gp, 20, 13);i++;
        gp.iTile[i] = new interactive_tiles.IT_Campfire(gp, 18, 21);i++;
    }

}