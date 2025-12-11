package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.characters.gaulois.Druid;
import src.main.java.Enum.character.Faction;
import src.main.java.food.Food;
import src.main.java.Enum.food.FoodType;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodCategory;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Druid panoramix;

    @BeforeEach
    void setUp() {
        // On recrée un personnage neuf avant chaque test
        panoramix = new Druid("Panoramix", 'M', 170, 50, 10, 10, Faction.GAULS);
        // On s'assure qu'il n'a pas faim au début (ou qu'il a faim selon votre logique)
        // Disons qu'on lui donne un peu faim pour qu'il puisse manger
        // (Supposons que 0 = a faim, 100 = rassasié, selon votre implémentation Statistics)
    }

    @Test
    void testTakeDamage() {
        // 1. Dégâts normaux
        int santeInitiale = panoramix.getHealth().get(); // 100
        panoramix.takeDamage(20);
        assertEquals(santeInitiale - 20, panoramix.getHealth().get(), "La santé doit diminuer correctement (100 -> 80)");

        // 2. Dégâts entraînant la mort (vérification du clamping à 0)
        panoramix.takeDamage(200); // 80 - 200 = -120, doit être 0
        assertEquals(0, panoramix.getHealth().get(), "La santé ne doit pas descendre sous 0");
    }

    @Test
    void testHeal() {
        // 1. Préparation : On blesse le personnage
        panoramix.takeDamage(50);
        int santeActuelle = panoramix.getHealth().get(); // 50

        // 2. Soin normal
        panoramix.heal(10);
        assertEquals(santeActuelle + 10, panoramix.getHealth().get(), "Le soin doit augmenter la santé (50 -> 60)");

        // 3. Soin excédentaire (vérification du clamping à 100)
        panoramix.heal(200); // 60 + 200 = 260, doit être 100
        assertEquals(100, panoramix.getHealth().get(), "La santé ne doit pas dépasser 100 (Max)");
    }

    @Test
    void testIsDead() {
        // 1. Personnage en bonne santé
        assertFalse(panoramix.isDead(), "Le personnage ne doit pas être considéré comme mort à 100 PV");

        // 2. Personnage blessé mais pas mort
        panoramix.takeDamage(50);
        assertFalse(panoramix.isDead(), "Le personnage ne doit pas être considéré comme mort à 50 PV");

        // 3. Personnage mort
        panoramix.takeDamage(100); // Il est à 0 PV
        assertTrue(panoramix.isDead(), "Le personnage doit être considéré comme mort à 0 PV");
    }

    @Test
    void testMangerPoissonPasFrais() {
        // Scénario : Manger du poisson pas frais est mauvais pour la santé
        panoramix.takeDamage(50); // Blesse le perso pour pouvoir tester la baisse de PV

        int santeInitiale = panoramix.getHealth().get(); // 50

        // On utilise ici une nutrition de 40 pour que le malus PV (nutrition/2) soit 20
        Food poissonPourri = new Food("Poisson pas frais", 40, FoodType.NOT_FRESH_FISH, FoodFreshness.NOT_FRESH, FoodCategory.FISH);

        panoramix.eat(poissonPourri);

        // La santé doit avoir baissé (50 - 20 = 30 si votre logique s'applique)
        assertTrue(panoramix.getHealth().get() < santeInitiale, "La santé doit baisser après avoir mangé du poisson pas frais");
    }

    @Test
    void testMangerLegumesConsecutifs() {
        // SETUP : On blesse le personnage (sinon il reste bloqué à 100 PV)
        panoramix.takeDamage(50); // Il tombe à 50 PV
        int santeAvantManger = panoramix.getHealth().get(); // 50

        Food carotte = new Food("Carotte", 10, FoodType.CARROT, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
        Food salade = new Food("Salade", 10, FoodType.STRAWBERRY, FoodFreshness.FRESH, FoodCategory.VEGETABLE);

        // --- 1er légume : tout va bien, on soigne ---
        panoramix.eat(carotte);
        // Il gagne 10 PV (nutrition), donc 50 + 10 = 60
        assertEquals(santeAvantManger + 10, panoramix.getHealth().get(), "Le premier légume doit soigner");

        // --- 2ème légume consécutif : Malade ! ---
        int santeAvantDeuxieme = panoramix.getHealth().get(); // 60
        panoramix.eat(salade);

        // Le 2ème légume fait perdre 10 PV (selon la correction de la méthode eat proposée), donc 60 - 10 = 50
        assertTrue(panoramix.getHealth().get() < santeAvantDeuxieme, "Le deuxième légume consécutif doit rendre malade");
        assertEquals(santeAvantDeuxieme - 10, panoramix.getHealth().get());
    }

    @Test
    void testGauloisRefuseNourritureRomaine() {
        // Scénario : Les Gaulois ont des préférences alimentaires et refusent ce qui n'est pas "leur" nourriture (sauf les légumes/poisson pas frais)
        int faimInitiale = panoramix.getHunger().get();
        int santeInitiale = panoramix.getHealth().get();

        // Le Miel (HONEY) n'est pas dans la liste 'gaulsFoods' de votre classe Character
        Food miel = new Food("Miel", 50, FoodType.HONEY, FoodFreshness.FRESH, FoodCategory.DRINK);

        panoramix.eat(miel);

        // Vérification : Ni la faim, ni la santé ne doivent changer car il refuse de manger
        assertEquals(faimInitiale, panoramix.getHunger().get(), "La faim ne doit pas changer (refus)");
        assertEquals(santeInitiale, panoramix.getHealth().get(), "La santé ne doit pas changer (refus)");
    }
}