package src.Characters;

import src.Characters.Gaulois.Blacksmith;
import src.Characters.Gaulois.Druid;
import src.Characters.Gaulois.Innkeeper;
import src.Characters.Gaulois.Merchant;
import src.Characters.Romain.General;
import src.Characters.Romain.Legionnaire;
import src.Characters.Fantastique.Lycanthrope;
import src.Characters.Romain.Prefect;
import src.Enum.Faction;
import src.Enum.Occupation;

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
                return new Blacksmith(name, sex, height, age, strength, stamina);
            case DRUID:
                stamina += 30;
                return new Druid(name, sex, height, age, strength, stamina);
            case MARCHAND:
                strength += 5;
                stamina += 5;
                return new Merchant(name, sex, height, age, strength, stamina);
            case AUBERGISTE:
                stamina += 25;
                return new Innkeeper(name, sex, height, age, strength, stamina);
            case LEGIONNAIRE:
                strength += 10;
                stamina += 15;
                return new Legionnaire(name, sex, height, age, strength, stamina);
            case PREFET:
                strength += 10;
                stamina += 10;
                return new Prefect(name, sex, height, age, strength, stamina);
            case GENERAL:
                strength += 15;
                stamina += 15;
                return new General(name, sex, height, age, strength, stamina);
            case LYCANTHROPE:
                strength += 30;
                stamina += 10;
                return new Lycanthrope(name, sex, height, age, strength, stamina);
            default:
                throw new IllegalArgumentException("Métier inconnu: " + occupation);
        }
    }
}
