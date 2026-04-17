package main;

public class AssetSetter {

    GamePannel gp;

    public AssetSetter(GamePannel gp) {
        this.gp = gp;
    }

    public void setObject() {} 

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
        gp.monster[i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 10;
        gp.monster[i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[i] = new monster.MON_BlueSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 10;
        gp.monster[i].worldY = gp.tileSize * 20;
    }


}