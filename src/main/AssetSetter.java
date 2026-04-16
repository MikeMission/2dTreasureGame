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
        gp.monster[0] = new monster.MON_BlueSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 17;
        gp.monster[0].worldY = gp.tileSize * 24;

        gp.monster[1] = new monster.MON_BlueSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 19;
        gp.monster[1].worldY = gp.tileSize * 28;
    }


}