package src.Place;

import src.Characters.Character;
import src.Place.Enum.TypePlace;
import src.Food.Food;

import java.util.ArrayList;
import java.util.Random;

public class PlaceFactory {
    Random rand = new Random();

    public SafePlace createSafePlace(String name, TypePlace typePlace, Character clanChief) {
        int rand_surface = 5_000 + rand.nextInt(20_000);

        return switch (typePlace) {
            case GAULS_VILLAGE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief);
            case ROMAN_FORTIFIED_CAMP -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief);
            case ROMAN_CITY -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief);
            case GALLO_ROMAN_VILLAGE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief);
            case ENCLOSURE -> new SafePlace(name, rand_surface, typePlace, new ArrayList<Character>(), new ArrayList<Food>(), clanChief);
//            case BATTLEFIELD -> new BattlePlace(name, rand_surface, typePlace); A faire plus tard
            default -> throw new IllegalArgumentException("/!\\ Quelle est cette endroit: " + typePlace);
        };
    }
}
