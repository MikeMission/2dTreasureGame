package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    // plr stats
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strength;
    int agility;
    int exp;
    int nextLevelExp;
    int coin;

    // plr location
    int x;
    int y;
    int currentMap;

    // current things
    int currentQuestID;
    int currentArea;
    int currentTrack;

    // plr inventory
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // objects on the map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
    
}
