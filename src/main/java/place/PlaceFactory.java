package src.main.java.place;

import src.main.java.characters.Character;
import src.main.java.Enum.place.TypePlace;
import src.main.java.food.Food;

import java.util.ArrayList;
import java.util.Random;

public class PlaceFactory {
    Random rand = new Random();

    /**
     * Create safe place easily
     * @param name
     * @param typePlace
     * @param clanChief
     * @return
     */
    public SafePlace createSafePlace(String name, TypePlace typePlace, Character clanChief) {
        int rand_surface = 5_000 + rand.nextInt(20_000);
        int randGauls = rand.nextInt(10,15);
        int randRoman = rand.nextInt(10, 15);
        int randLycantrope = rand.nextInt(10, 15);

        return switch (typePlace) {
            case GAULS_VILLAGE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief, randGauls, 0, randLycantrope);
            case ROMAN_FORTIFIED_CAMP -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief, 0, randRoman, randLycantrope);
            case ROMAN_CITY -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief, 0, randRoman, randLycantrope);
            case GALLO_ROMAN_VILLAGE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief, randGauls, randRoman, 0);
            case ENCLOSURE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief, 0, 0, randLycantrope);
            default -> throw new IllegalArgumentException("/!\\ Quelle est cette endroit: " + typePlace);
        };
    }

    /**
     * Create battlefield easily
     * @param name
     * @param typePlace
     * @return
     */
    public BattleField createBattleField(String name, TypePlace typePlace) {
        int rand_surface = 5_000 + rand.nextInt(20_000);
        return switch (typePlace) {
            case GAULS_VILLAGE -> null;
            case ROMAN_FORTIFIED_CAMP -> null;
            case ROMAN_CITY -> null;
            case GALLO_ROMAN_VILLAGE -> null;
            case ENCLOSURE -> null;
            case BATTLEFIELD -> new BattleField(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>());
        };
    }
}
