package src.test;


import org.junit.jupiter.api.Test;
import src.main.java.Statistics;
import static org.junit.jupiter.api.Assertions.*;


class StatisticsTest {

    @Test
    void testClampRespectsBounds() {
        // Initialisation : valeur 50, min 0, max 100
        Statistics stat = new Statistics(50, 0, 100);

        // Test dépassement Max
        stat.add(200);
        assertEquals(100, stat.get(), "La valeur ne devrait pas dépasser le max (100)");

        // Test dépassement Min
        stat.add(-500);
        assertEquals(0, stat.get(), "La valeur ne devrait pas descendre sous le min (0)");
    }

    @Test
    void testIncreaseMax() {
        Statistics stat = new Statistics(100, 0, 100);

        // On augmente le max de 50
        stat.increaseMax(50);
        assertEquals(150, stat.getMax(), "Le max devrait être passé à 150");

        // La valeur actuelle ne doit pas changer tant qu'on n'ajoute rien
        assertEquals(100, stat.get());

        // Maintenant on peut monter jusqu'à 150
        stat.add(20);
        assertEquals(120, stat.get());
    }
}