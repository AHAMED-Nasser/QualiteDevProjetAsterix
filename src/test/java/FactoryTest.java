package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.characters.CharacterFactory;
import src.main.java.characters.Character;
import src.main.java.characters.gaulois.Blacksmith;
import src.main.java.food.FoodFactory;
import src.main.java.food.Food;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.character.Occupation;
import src.main.java.Enum.food.FoodType;
import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void testCharacterFactoryStats() {
        CharacterFactory factory = new CharacterFactory();

        // Création d'un forgeron (Bonus Force +30)
        Character forgeron = factory.createCharacter(Faction.GAULS, Occupation.FORGERON, "Cétautomatix");

        assertTrue(forgeron instanceof Blacksmith);
        assertEquals("Cétautomatix", forgeron.getName());

        // Vérification basique des stats (Force min 50 + 30 bonus = 80 min)
        assertTrue(forgeron.getStrength() >= 80, "La force du forgeron doit être boostée");
    }

    @Test
    void testFoodFactoryValues() {
        FoodFactory factory = new FoodFactory();

        Food sanglier = factory.createFood(FoodType.WILD_BOAR);
        assertEquals(60, sanglier.getFoodNutrition());
        assertEquals("Sanglier", sanglier.getName());

        Food poison = factory.createFood(FoodType.NOT_FRESH_FISH);
        // Vérifie que la valeur est bien négative comme défini dans la factory
        assertEquals(-30, poison.getFoodNutrition());
    }
}