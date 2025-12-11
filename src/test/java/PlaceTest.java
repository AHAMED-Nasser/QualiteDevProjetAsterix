package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.place.*;
import src.main.java.characters.Character;
import src.main.java.characters.ClanChief;
import src.main.java.characters.gaulois.Druid;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.place.TypePlace;
import src.main.java.food.Food;
import src.main.java.Enum.food.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    private SafePlace village;
    private BattleField champBataille;
    private ClanChief chef;

    @BeforeEach
    void setUp() {
        chef = new ClanChief("Abraracourcix", 'M', 100, 50, 10, 10, Faction.GAULS);

        // Création d'un village avec 0 habitants au départ pour contrôler le test
        village = new SafePlace("Village", 1000, TypePlace.GAULS_VILLAGE,
                new ArrayList<>(), new ArrayList<>(), chef, 0, 0, 0);

        champBataille = new BattleField("Plaine", 2000, TypePlace.BATTLEFIELD,
                new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testAddAndRemoveCharacter() {
        Druid druide = new Druid("Panoramix", 'M', 1, 1, 1, 1, Faction.GAULS);

        village.addCharacter(druide);
        assertEquals(1, village.getNumberOfCharacters());
        assertTrue(village.getCharacterList().contains(druide));

        village.removeCharacter(druide);
        assertEquals(0, village.getNumberOfCharacters());
    }

    @Test
    void testTransferTroops() {
        // On ajoute 5 druides au village
        for (int i = 0; i < 5; i++) {
            village.addCharacter(new Druid("Druide"+i, 'M', 1, 1, 1, 1, Faction.GAULS));
        }
        assertEquals(5, village.getNumberOfCharacters());
        assertEquals(0, champBataille.getNumberOfCharacters());

        // Transfert de 3 personnages vers le champ de bataille
        village.transferCharacter(3, champBataille);

        assertEquals(2, village.getNumberOfCharacters(), "Il doit rester 2 persos au village");
        assertEquals(3, champBataille.getNumberOfCharacters(), "Il doit y avoir 3 persos au front");
    }

    @Test
    void testFoodManagement() {
        int actualNbFood = village.getFoodList().size();
        Food pomme = new Food("Pomme", 10, FoodType.STRAWBERRY, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
        village.addFood(pomme);

        assertEquals(actualNbFood + 1, village.getFoodList().size());
        assertEquals("Pomme", village.getFoodList().getLast().getName());
    }
}