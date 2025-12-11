package src;

import src.main.java.Enum.character.Faction;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.characters.Character;
import src.main.java.characters.ClanChief;
import src.main.java.characters.IFighter;
import src.main.java.food.Food;
import src.main.java.food.FoodFactory;
import src.main.java.food.StockFood;
import src.main.java.place.BattleField;
import src.main.java.place.Place;
import src.main.java.Enum.place.TypePlace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InvasionTheater {

    private String name;
    private List<Place> places;
    private List<ClanChief> chiefs;
    private Scanner scanner;
    private Random random;
    private StockFood stockFoodGenerator; // Pour faire apparaître de la nourriture

    public InvasionTheater(String name, List<Place> places, List<ClanChief> chiefs) {
        this.name = name;
        this.places = places;
        this.chiefs = chiefs;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.stockFoodGenerator = new StockFood();
    }

    public void run() {
        boolean running = true;
        int turn = 1;

        while (running) {
            System.out.println("\n==========================================");
            System.out.println("     CYCLE TEMPOREL " + turn + " - " + this.name.toUpperCase());
            System.out.println("==========================================");

            // 1. Gestion des Combats (Champs de bataille)
            handleBattles();

            // 2. Évolution de l'état des personnages (Faim, Potion)
            updateCharactersState();

            // 3. Apparition de nourriture (Lieux normaux)
            spawnFood();

            // 4. Pourrissement de la nourriture
            rotFood();

            // 5. Interaction Joueur (Chef de Clan)
            handlePlayerTurn();

            turn++;

            // Petite pause pour ne pas spammer la console si on veut lire
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void handleBattles() {
        System.out.println("\n--- [1] Phase de Combat ---");
        for (Place place : places) {
            if (place instanceof BattleField) {
                System.out.println("Combats sur : " + place.getName());
                List<Character> fighters = place.getCharacterList();

                // Simulation simple : On prend deux persos au hasard et ils se battent
                if (fighters.size() >= 2) {
                    for (int i = 0; i < fighters.size() / 2; i++) {
                        Character c1 = fighters.get(random.nextInt(fighters.size()));
                        Character c2 = fighters.get(random.nextInt(fighters.size()));

                        // Ils ne se battent que s'ils sont ennemis et combattants
                        if (c1 != c2 && c1.getFaction() != c2.getFaction()
                                && c1 instanceof IFighter && c2 instanceof IFighter) {

                            ((IFighter) c1).fight(c2);

                            // Exemple de dégâts simplifiés (car votre méthode fight ne fait que des sysout pour l'instant)
                            // c2.takeDamage(c1.getStrength() / 10);
                        }
                    }
                } else {
                    System.out.println("  Pas assez de combattants ici.");
                }
            }
        }
    }

    private void updateCharactersState() {
        System.out.println("\n--- [2] État des Personnages ---");
        for (Place place : places) {
            for (Character c : place.getCharacterList()) {
                if(c.isDead()) continue;

                // La faim augmente (donc la barre descend)
                c.getHunger().add(-5);

                // La potion diminue
                if (c.getMagicPotion().get() > 0) {
                    c.getMagicPotion().add(-10);
                    if (c.getMagicPotion().get() == 0) System.out.println(c.getName() + " n'a plus d'effet de potion.");
                }

                // Alerte faim
                if (c.getHunger().get() < 20) {
                    System.out.println(c.getName() + " a très faim (" + c.getHunger().get() + "/100) !");
                }
            }
        }
    }

    private void spawnFood() {
        // Apparition aléatoire dans les lieux non-bataille
        for (Place place : places) {
            if (place.getTypePlace() != TypePlace.BATTLEFIELD && random.nextInt(10) < 3) { // 30% de chance
                List<Food> newFoods = stockFoodGenerator.generateInitialStock(1); // On en génère 1
                if (!newFoods.isEmpty()) {
                    place.addFood(newFoods.getFirst());
                    System.out.println("Nouveau vivres apparus à " + place.getName() + " : " + newFoods.get(0).getName());
                }
            }
        }
    }

    private void rotFood() {
        // La nourriture fraîche devient "passable", la "passable" devient "pas fraîche"
        for (Place place : places) {
            for (Food food : place.getFoodList()) {
                if (food.getFoodFreshness() == FoodFreshness.FRESH && random.nextBoolean()) {
                    food.setFoodFreshness(FoodFreshness.FAIRLY_FRESH);
                } else if (food.getFoodFreshness() == FoodFreshness.FAIRLY_FRESH && random.nextBoolean()) {
                    food.setFoodFreshness(FoodFreshness.NOT_FRESH);
                }
            }
        }
    }

    private void handlePlayerTurn() {
        System.out.println("\n--- [5] Tour du Chef de Clan ---");
        // On sélectionne le premier chef par défaut pour l'exemple
        if (chiefs.isEmpty()) return;
        ClanChief currentChief = chiefs.get(0);

        System.out.println("Chef actuel : " + currentChief.getName());
        System.out.println("1. Ne rien faire (Passer le tour)");
        System.out.println("2. Voir les statistiques du théâtre");
        System.out.print("Votre choix : ");

        // Pour éviter de bloquer la simulation si vous n'entrez rien,
        // vous pouvez commenter le scanner le temps des tests automatiques.
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            if (choice == 2) {
                displayGlobalStats();
            }
        } else {
            scanner.next(); // consome l'entrée invalide
        }
    }

    private void displayGlobalStats() {
        System.out.println("=== STATISTIQUES ===");
        for (Place p : places) {
            System.out.println("Lieu : " + p.getName() + " | Pop : " + p.getNumberOfCharacters());
        }
    }
}