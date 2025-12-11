package src.main.java.characters;

import src.main.java.Enum.character.Faction;

public class ClanChief extends Character implements IClanChief {

    /**
     * Clan chief Constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
    public ClanChief(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }
}
