package src.main.java;

import src.main.java.characters.Character;
import src.main.java.characters.ClanChief;
import src.main.java.characters.IFighter;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.place.TypePlace;
import src.main.java.food.Food;
import src.main.java.food.StockFood;
import src.main.java.place.BattleField;
import src.main.java.place.Place;
import src.main.java.place.SafePlace;
import src.main.java.place.ISafePlace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InvasionTheater {

    private String name;
    private List<Place> places;
    private List<ClanChief> chiefs;
    private Scanner scanner;
    private Random random;
    private StockFood stockFoodGenerator;

    // Contrôle du thread de temps
    private volatile boolean isPaused = false;

    public InvasionTheater(String name, List<Place> places, List<ClanChief> chiefs) {
        this.name = name;
        this.places = places;
        this.chiefs = chiefs;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.stockFoodGenerator = new StockFood();
    }

    // Nouvelle méthode run() qui utilise deux threads
    public void run() {

        // --- THREAD DE TEMPS : Le moteur de la simulation ---
        Thread timeThread = new Thread(() -> {
            int turn = 1;
            while (true) {
                if (!isPaused) {
                    try {
                        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
                        System.out.println("║   ANNÉE 50 AV. J.C. - SEMAINE " + turn + " - THÉÂTRE : " + this.name.toUpperCase() + "   ║");
                        System.out.println("╚════════════════════════════════════════════════════════════════╝");

                        handleAdvancedBattles();
                        updateCharactersStateAndCleanup();
                        handleFoodCycle();
                        checkEndGameConditions();

                        turn++;

                        System.out.println("\n[SIMULATION] Le temps s'écoule... (Prochain tour dans 5s)");
                        System.out.println("   (Tapez Entrée à tout moment pour prendre la main !)");
                        Thread.sleep(5000); // Le temps avance de 5 secondes

                    } catch (InterruptedException e) {
                        System.out.println("Arrêt forcé du temps.");
                        break;
                    }
                } else {
                    // Si en pause, on attend juste un peu sans bloquer
                    try { Thread.sleep(500); } catch (InterruptedException e) { break; }
                }
            }
        });

        // Démarrage du thread de temps
        timeThread.start();

        // --- THREAD PRINCIPAL : Écoute des commandes utilisateur ---
        Scanner inputListener = new Scanner(System.in);
        while (true) {
            // Le scanner bloque jusqu'à ce que l'utilisateur tape Entrée
            inputListener.nextLine();

            // Si le temps n'est pas déjà en pause, on le met en pause pour le joueur
            if (!isPaused) {
                isPaused = true;
                System.out.println("\n🛑 TEMPS ARRÊTÉ ! À VOUS DE JOUER CHEF !");
                handlePlayerTurn(); // Menu d'action du chef de clan

                System.out.println("▶️ REMISE EN ROUTE DU TEMPS...");
                isPaused = false;
            }
        }
    }


    // --- LOGIQUE DE COMBAT AVANCÉE (handleAdvancedBattles) ---
    private void handleAdvancedBattles() {
        System.out.println("\n⚔️ --- PHASE DE BATAILLE --- ⚔️");
        for (Place place : places) {
            if (place instanceof BattleField) {
                List<Character> fighters = place.getCharacterList();
                if (fighters.size() < 2) {
                    System.out.println("   Le champ de bataille " + place.getName() + " est calme.");
                    continue;
                }

                System.out.println("   Des cris de guerre retentissent sur : " + place.getName() + " !");

                List<Character> activeFighters = new ArrayList<>(fighters);
                java.util.Collections.shuffle(activeFighters);

                int battlesCount = 0;
                for (int i = 0; i < activeFighters.size() - 1; i += 2) {
                    Character c1 = activeFighters.get(i);
                    Character c2 = activeFighters.get(i+1);

                    if (c1.getFaction() != c2.getFaction() && !c1.isDead() && !c2.isDead()
                            && c1 instanceof IFighter && c2 instanceof IFighter) {
                        resolveDuel(c1, c2);
                        battlesCount++;
                    }
                }
                if (battlesCount == 0) System.out.println("   Les ennemis s'observent sans s'attaquer...");
            }
        }
    }

    private void resolveDuel(Character attacker, Character defender) {
        int atkPower = attacker.getStrength();
        if (attacker.getMagicPotion().get() > 0) {
            atkPower *= 3; // La potion triple la force !
            System.out.print("⚡ (Potion) ");
        }

        int defResistance = defender.getStamina() / 2;
        int damage = Math.max(1, atkPower - defResistance + random.nextInt(10));

        System.out.println(attacker.getName() + " (" + attacker.getFaction() + ") frappe " + defender.getName() + " pour " + damage + " dégâts !");
        defender.takeDamage(damage);

        if (!defender.isDead()) {
            int ripPower = defender.getStrength();
            if (defender.getMagicPotion().get() > 0) ripPower *= 3;
            int ripDamage = Math.max(1, ripPower - (attacker.getStamina() / 2));
            System.out.println("   " + defender.getName() + " riposte ! (-" + ripDamage + " PV)");
            attacker.takeDamage(ripDamage);
        } else {
            System.out.println("   💀 " + defender.getName() + " s'écroule !");
        }
    }


    // --- GESTION DES ÉTATS ET NETTOYAGE (updateCharactersStateAndCleanup) ---
    private void updateCharactersStateAndCleanup() {
        System.out.println("\n🍂 --- BILAN DE SANTÉ --- 🍂");
        for (Place place : places) {
            Iterator<Character> it = place.getCharacterList().iterator();
            while (it.hasNext()) {
                Character c = it.next();

                // 1. Retrait des morts (après le combat ou par faim)
                if (c.isDead()) {
                    System.out.println("   ✝ " + c.getName() + " est retiré du lieu " + place.getName());
                    it.remove();
                    continue;
                }

                // 2. Faim qui augmente
                c.getHunger().add(-5);
                if (c.getHunger().get() <= 0) {
                    System.out.println("   " + c.getName() + " est épuisé et meurt de faim !");
                    c.takeDamage(1000); // Mort instantanée
                }

                // 3. Potion qui diminue
                if (c.getMagicPotion().get() > 0) {
                    c.getMagicPotion().add(-10);
                    if (c.getMagicPotion().get() <= 0) {
                        System.out.println("   L'effet de la potion se dissipe pour " + c.getName());
                    }
                }
            }
        }
    }


    // --- NOURRITURE (handleFoodCycle) ---
    private void handleFoodCycle() {
        // Apparition
        for (Place place : places) {
            if (!(place instanceof BattleField) && random.nextInt(100) < 30) {
                List<Food> loot = stockFoodGenerator.generateInitialStock(1);
                if (!loot.isEmpty()) {
                    place.addFood(loot.get(0));
                }
            }
        }

        // Pourrissement
        for (Place place : places) {
            for (Food food : place.getFoodList()) {
                if (food.getFoodFreshness() == FoodFreshness.FRESH && random.nextInt(100) < 50) { // 50% de chance
                    food.setFoodFreshness(FoodFreshness.FAIRLY_FRESH);
                } else if (food.getFoodFreshness() == FoodFreshness.FAIRLY_FRESH && random.nextInt(100) < 50) {
                    food.setFoodFreshness(FoodFreshness.NOT_FRESH);
                }
            }
        }
    }


    // --- INTERACTIONS CHEF DE CLAN (handlePlayerTurn) ---
    private void handlePlayerTurn() {
        System.out.println("\n👑 --- MENU CHEF DE CLAN --- 👑");

        List<SafePlace> safePlaces = places.stream()
                .filter(p -> p instanceof SafePlace)
                .map(p -> (SafePlace) p)
                .collect(Collectors.toList());

        System.out.println("Quel lieu voulez-vous administrer ?");
        for (int i = 0; i < safePlaces.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + safePlaces.get(i).getName() + " (Pop: " + safePlaces.get(i).getNumberOfCharacters() + ")");
        }
        System.out.println(" 0. Retour à la simulation");

        int choice = getIntInput(safePlaces.size());
        if (choice == 0) return;

        SafePlace selectedPlace = safePlaces.get(choice - 1);
        managePlace(selectedPlace);
    }

    private void managePlace(SafePlace place) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- GESTION : " + place.getName().toUpperCase() + " ---");
            System.out.println("1. Examiner le lieu (Infos & Troupes)");
            System.out.println("2. Nourrir tout le monde (Utilise le stock)");
            System.out.println("3. Soigner tout le monde");
            System.out.println("4. Envoyer des troupes au front");
            // 5. Demander au druide de faire de la potion (À implémenter)
            System.out.println("5. Retour");

            int action = getIntInput(5);
            switch (action) {
                case 1:
                    place.displayPlaceInfo();
                    place.displayCharacterMinInfo();
                    place.displayFood();
                    break;
                case 2:
                    // Remarque : Votre SafePlace::feedAllCharacters met juste la faim à 100
                    // Il faudrait idéalement une logique qui consomme le stock de nourriture !
                    place.feedAllCharacters();
                    System.out.println("Le banquet est terminé ! (Le stock n'a pas été vérifié)");
                    break;
                case 3:
                    place.healAllCharacters();
                    System.out.println("Les soins ont été prodigués.");
                    break;
                case 4:
                    sendTroopsMenu(place);
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }

    private void sendTroopsMenu(SafePlace source) {
        List<BattleField> battleFields = places.stream()
                .filter(p -> p instanceof BattleField)
                .map(p -> (BattleField) p)
                .collect(Collectors.toList());

        if (battleFields.isEmpty()) {
            System.out.println("Aucun champ de bataille disponible !");
            return;
        }

        System.out.println("Vers quel champ de bataille ?");
        for (int i = 0; i < battleFields.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + battleFields.get(i).getName());
        }
        System.out.println(" 0. Annuler");

        int bfIndex = getIntInput(battleFields.size());
        if (bfIndex == 0) return;

        System.out.println("Combien de soldats envoyer ? (Max " + source.getNumberOfCharacters() + ")");
        int count = getIntInput(source.getNumberOfCharacters());

        if (count > 0) {
            source.transferCharacter(count, battleFields.get(bfIndex - 1));
            System.out.println(count + " braves sont partis au combat !");
        }
    }

    private int getIntInput(int max) {
        System.out.print("Choix > ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Choix > ");
        }
        int val = scanner.nextInt();
        return Math.max(0, Math.min(val, max));
    }


    // --- CONDITIONS DE FIN (checkEndGameConditions) ---
    private void checkEndGameConditions() {
        long gaulsAlive = places.stream().flatMap(p -> p.getCharacterList().stream()).filter(c -> c.getFaction() == Faction.GAULS).count();
        long romansAlive = places.stream().flatMap(p -> p.getCharacterList().stream()).filter(c -> c.getFaction() == Faction.ROMAN).count();

        if (gaulsAlive == 0) {
            System.out.println("\n[FIN] TOUTE LA GAULE EST OCCUPÉE... (Victoire Romaine)");
            System.exit(0);
        } else if (romansAlive == 0) {
            System.out.println("\n[FIN] ILS SONT FOUS CES ROMAINS ! (Victoire Gauloise)");
            System.exit(0);
        }
    }
}