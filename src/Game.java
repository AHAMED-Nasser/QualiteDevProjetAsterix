package src;

import src.Characters.Character;
import src.Characters.CharacterFactory;
import src.Enum.Character.Faction;
import src.Enum.Character.Occupation;
import src.Food.Food;
import src.Food.FoodFactory;
import src.Enum.Food.FoodType;
import src.Enum.Place.TypePlace;
import src.Place.PlaceFactory;
import src.Place.SafePlace;

public class Game {

    public static void main(String[] var0) {

        // Characters
        CharacterFactory var1 = new CharacterFactory();
        Character var2 = var1.createCharacter(Faction.GAULS, Occupation.DRUID, "Panoramix");
        Character var3 = var1.createCharacter(Faction.GAULS, Occupation.FORGERON, "Cétautomatix");
        Character var4 = var1.createCharacter(Faction.GAULS, Occupation.MARCHAND, "Ordralfabétix");
        Character var5 = var1.createCharacter(Faction.GAULS, Occupation.AUBERGISTE, "Odalix");
        Character var6 = var1.createCharacter(Faction.ROMAN, Occupation.LEGIONNAIRE, "Pamplemus");
        Character var7 = var1.createCharacter(Faction.ROMAN, Occupation.PREFET, "Bonusmalus");
        Character var8 = var1.createCharacter(Faction.ROMAN, Occupation.GENERAL, "Jules César");
        Character var9 = var1.createCharacter(Faction.FANTASTIC_CREATURE, Occupation.LYCANTHROPE, "Prolix");

        Character chef_clan = var1.createCharacter(Faction.GAULS, Occupation.CLAN_CHIEF, "Nasser");

        // Food
        FoodFactory foodFactory = new FoodFactory();

        Food sanglier = foodFactory.createFood(FoodType.WILD_BOAR);
        Food poisson_pas_frais = foodFactory.createFood(FoodType.NOT_FRESH_FISH);
        Food fraise = foodFactory.createFood(FoodType.STRAWBERRY);

        // PLACE

        PlaceFactory placeFactory = new PlaceFactory();

        SafePlace pydertale = placeFactory.createSafePlace("Pydertale", TypePlace.GAULS_VILLAGE, chef_clan);

        System.out.println("--- Début de la simulation ---");

        pydertale.addCharacter(var2);
        pydertale.addCharacter(var3);

        pydertale.displayCharacterMinInfo();

        var2.setHunger(new Statistics(50, 0, 100));

        pydertale.displayCharacterMinInfo();

        pydertale.feedOneCharacters(var2);

        pydertale.displayCharacterMinInfo();
    }
}
