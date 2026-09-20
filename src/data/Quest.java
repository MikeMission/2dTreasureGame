package data;

public class Quest {

    public String title;
    public String description;
    public int id;

    public Quest(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public static Quest getQuestFromID(int i, Quest[] quests) {
        Quest foundQuest = null;
        
        for (Quest quest : quests) {
            if (i == quest.id) {
                foundQuest = quest;
            }
        }

        return foundQuest;
    }
}
