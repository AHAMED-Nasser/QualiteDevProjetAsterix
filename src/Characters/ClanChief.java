package src.Characters;

import src.Enum.Character.Faction;
import src.Food.Food;
import src.Interfaces.IClanChief;
import src.Characters.Character;

public class ClanChief extends Character implements IClanChief {

    public ClanChief(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }
}
