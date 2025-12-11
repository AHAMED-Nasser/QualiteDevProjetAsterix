package src.main.java.characters;

import src.main.java.characters.gaulois.Blacksmith;
import src.main.java.characters.gaulois.Druid;
import src.main.java.characters.gaulois.Innkeeper;
import src.main.java.characters.gaulois.Merchant;
import src.main.java.characters.romain.General;
import src.main.java.characters.romain.Legionnaire;
import src.main.java.characters.fantastique.Lycanthrope;
import src.main.java.characters.romain.Prefect;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.character.Occupation;

import java.util.Random;

public class CharacterFactory {
    private Random random = new Random();

    public Character createCharacter(Faction faction, Occupation occupation, String name) {
        int height = 140 + random.nextInt(60);
        int age = 18 + random.nextInt(70);
        int strength = 50 + random.nextInt(50);
        int stamina = 50 + random.nextInt(50);
        char sex = random.nextBoolean() ? 'M' : 'F';

        switch (occupation) {
            case FORGERON:
                strength += 30;
                return new Blacksmith(name, sex, height, age, strength, stamina, faction);
            case DRUID:
                stamina += 30;
                return new Druid(name, sex, height, age, strength, stamina, faction);
            case MARCHAND:
                strength += 5;
                stamina += 5;
                return new Merchant(name, sex, height, age, strength, stamina, faction);
            case AUBERGISTE:
                stamina += 25;
                return new Innkeeper(name, sex, height, age, strength, stamina, faction);
            case LEGIONNAIRE:
                strength += 10;
                stamina += 15;
                return new Legionnaire(name, sex, height, age, strength, stamina, faction);
            case PREFET:
                strength += 10;
                stamina += 10;
                return new Prefect(name, sex, height, age, strength, stamina, faction);
            case GENERAL:
                strength += 15;
                stamina += 15;
                return new General(name, sex, height, age, strength, stamina, faction);
            case LYCANTHROPE:
                strength += 30;
                stamina += 10;
                return new Lycanthrope(name, sex, height, age, strength, stamina, faction);
            case CLAN_CHIEF:
                return new ClanChief(name, sex, 0, age, 0, 0, faction);
            default:
                throw new IllegalArgumentException("Métier inconnu: " + occupation);
        }
    }
}
