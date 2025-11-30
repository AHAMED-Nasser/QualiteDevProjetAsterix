package src.Characters;

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
            default:
                throw new IllegalArgumentException("Métier inconnu: " + occupation);
        }
    }
}
