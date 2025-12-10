package src.Characters;

import src.Enum.Character.Faction;
import src.Interfaces.Character.IClanChief;

public class ClanChief extends Character implements IClanChief {

    public ClanChief(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }
}
