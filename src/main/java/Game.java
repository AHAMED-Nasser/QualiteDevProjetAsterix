package src.main.java;

import src.main.java.InvasionTheater;
import src.main.java.characters.Character;
import src.main.java.characters.CharacterFactory;
import src.main.java.characters.ClanChief;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.character.Occupation;
import src.main.java.Enum.place.TypePlace;
import src.main.java.place.BattleField;
import src.main.java.place.Place;
import src.main.java.place.PlaceFactory;
import src.main.java.place.SafePlace;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public void run() {

        // 1. Création du Chef de Clan (Casting nécessaire car createCharacter renvoie Character)
        CharacterFactory factory = new CharacterFactory();
        Character charChef = factory.createCharacter(Faction.GAULS, Occupation.CLAN_CHIEF, "Abraracourcix");
        ClanChief chefDeClan = (ClanChief) charChef; // Casting sûr car on a demandé un CLAN_CHIEF

        // 2. Création des lieux
        PlaceFactory placeFactory = new PlaceFactory();

        SafePlace villageGaulois = placeFactory.createSafePlace("Village gaulois", TypePlace.GAULS_VILLAGE, chefDeClan);
        SafePlace campRetrancheRomain = placeFactory.createSafePlace("Camp retranché romain", TypePlace.ROMAN_FORTIFIED_CAMP, chefDeClan);
        BattleField champDeBattaille = placeFactory.createBattleField("Plaine des baffes", TypePlace.BATTLEFIELD);

        // 3. Transfert initial de troupes pour qu'il y ait de l'action
        System.out.println("--- Préparation de la simulation ---");
        villageGaulois.transferCharacter(5, champDeBattaille); // 5 Gaulois vont à la bagarre
        campRetrancheRomain.transferCharacter(5, champDeBattaille); // 5 Romains vont à la bagarre

        // 4. Initialisation du Théâtre d'Envahissement
        List<Place> allPlaces = new ArrayList<>();
        allPlaces.add(villageGaulois);
        allPlaces.add(campRetrancheRomain);
        allPlaces.add(champDeBattaille);

        List<ClanChief> chiefs = new ArrayList<>();
        chiefs.add(chefDeClan);

        InvasionTheater theater = new InvasionTheater("Armorique", allPlaces, chiefs);

        // 5. Lancement
        theater.run();
    }
}