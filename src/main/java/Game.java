package src.main.java;

import src.main.java.characters.Character;
import src.main.java.characters.CharacterFactory;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.character.Occupation;
import src.main.java.Enum.place.TypePlace;
import src.main.java.place.BattleField;
import src.main.java.place.PlaceFactory;
import src.main.java.place.SafePlace;

public class Game {

    public void run() {

        // Create clan chief
        CharacterFactory factory = new CharacterFactory();
        Character chef_clan = factory.createCharacter(Faction.GAULS, Occupation.CLAN_CHIEF, "Nasser");


        PlaceFactory placeFactory = new PlaceFactory();

        // SafePlace

        SafePlace villageGaulois = placeFactory.createSafePlace("Village gaulois", TypePlace.GAULS_VILLAGE, chef_clan);
        SafePlace campRetrancheRomain = placeFactory.createSafePlace("Camp retranché romain", TypePlace.ROMAN_FORTIFIED_CAMP, chef_clan);
        SafePlace villeRomain = placeFactory.createSafePlace("Ville romain", TypePlace.ROMAN_CITY, chef_clan);
        SafePlace bourgadeGalloRomaine = placeFactory.createSafePlace("Bourgade gallo-romaine", TypePlace.GALLO_ROMAN_VILLAGE, chef_clan);
        SafePlace enclos = placeFactory.createSafePlace("Enclos", TypePlace.ENCLOSURE, chef_clan);

        // Battle Field
        BattleField champDeBattaille = placeFactory.createBattleField("Champ de battaille", TypePlace.BATTLEFIELD);


        System.out.println("--- Début de la simulation ---");

        villageGaulois.transferCharacter(20, champDeBattaille);

        champDeBattaille.displayCharacterMinInfo();
        champDeBattaille.displayFood();

    }


}
