package src;

import src.Characters.Gaulois;
import src.Characters.Romain;

public class Simulation {
    public static void main(String[] args) {
        // --- 1. Création des personnages ---

        System.out.println("==================================================");
        System.out.println("              DÉBUT DE LA SIMULATION              ");
        System.out.println("==================================================");

        Gaulois obelix = new Gaulois("Obelix", "Homme", 1.95, 35, 80, 70, 90, 50, 70, 0, "Marchand");

        Romain legionnaire = new Romain("Aïeucédès", "Homme", 1.75, 25, 40, 50, 100, 75, 50, 0, "Légionnaire");

        System.out.println("\n⚔️ Les Combattants : \n");
        System.out.println(obelix.getName() + " (Gaulois) - Force initiale: " + obelix.getStrength() + " | Santé: " + obelix.getHealth());
        System.out.println(legionnaire.getName() + " (Romain) - Force initiale: " + legionnaire.getStrength() + " | Santé: " + legionnaire.getHealth());
        System.out.println("--------------------------------------------------");

        // --- 2. Scénario 1 : Combat équilibré (avant potion) ---

        System.out.println("\n### Scénario 1 : Combat Initial (Obélix non boosté) ###");

        // Obelix attack le légionnaire
        obelix.fight(legionnaire);

        if (legionnaire.isAlive()) {
            legionnaire.fight(obelix);
        }

        System.out.println("--- Après le combat 1 ---");
        System.out.println(obelix.getName() + " Santé: " + obelix.getHealth());
        System.out.println(legionnaire.getName() + " Santé: " + legionnaire.getHealth());
        System.out.println("--------------------------------------------------");

        // ---3. Utilisation de la potion magique par le Gaulois ---
        System.out.println("\n### Scénario 2 : Potion Magique ! ###");

        obelix.drinkMagicPotion();

        System.out.println(obelix.getName() + " Force après potion: " + obelix.getStrength());

        // Le Romain essaie de boir la potion
        legionnaire.drinkMagicPotion();

        System.out.println("--------------------------------------------------");

        // --- 4. Scénario 3 : Combat avec Potion ---

        System.out.println("\n### Scénario 3 : Combat boosté (Obélix vs Romain) ###");

        // Obélix attaque de nouveau (maintenant avec une force boostée)
        obelix.fight(legionnaire);

        // Le Romain riposte... s'il survit !
        if (legionnaire.isAlive()) {
            legionnaire.fight(obelix);
        }

        System.out.println("--- Après le combat 2 ---");
        System.out.println(obelix.getName() + " Santé: " + obelix.getHealth());
        System.out.println(legionnaire.getName() + " Santé: " + legionnaire.getHealth());
        System.out.println("--------------------------------------------------");

        // --- 5. Fin de l'effet de la Potion ---

        System.out.println("\n### Scénario 4 : Fin de l'effet ###");

        obelix.removePotionEffect();
        System.out.println(obelix.getName() + " Force après dissipation: " + obelix.getStrength());


        // --- 6. Soins et Repas ---

        System.out.println("\n### Scénario 5 : Soins et Repas ###");
        obelix.heal(20);
        obelix.eat();

        System.out.println("==================================================");
        System.out.println("              FIN DE LA SIMULATION                ");
        System.out.println("==================================================");
    }

}
