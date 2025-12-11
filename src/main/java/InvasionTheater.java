package src.main.java;

import src.main.java.characters.Character;
import src.main.java.characters.ClanChief;
import src.main.java.characters.IFighter;
import src.main.java.characters.IWorker;
import src.main.java.characters.gaulois.Druid;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;
import src.main.java.Enum.place.TypePlace;
import src.main.java.food.Food;
import src.main.java.food.FoodFactory;
import src.main.java.food.StockFood;
import src.main.java.place.BattleField;
import src.main.java.place.Place;
import src.main.java.place.SafePlace;

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
    private FoodFactory foodFactory; // Pour que les travailleurs créent de la nourriture

    // Contrôle du thread de temps
    private volatile boolean isPaused = false;

    public InvasionTheater(String name, List<Place> places, List<ClanChief> chiefs) {
        this.name = name;
        this.places = places;
        this.chiefs = chiefs;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.stockFoodGenerator = new StockFood();
        this.foodFactory = new FoodFactory();
    }

    public void run() {

        Thread timeThread = new Thread(() -> {
            int turn = 1;
            while (true) {
                if (!isPaused) {
                    try {
                        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
                        System.out.println("║   ANNÉE 50 AV. J.C. - SEMAINE " + turn + " - THÉÂTRE : " + this.name.toUpperCase() + "   ║");
                        System.out.println("╚════════════════════════════════════════════════════════════════╝");

                        // 1. La vie quotidienne (Manger, Travailler, Soigner)
                        handleDailyLife();

                        // 2. Les Combats
                        handleAdvancedBattles();

                        // 3. Gestion de l'environnement (Nourriture pourrit, morts disparaissent)
                        updateEnvironmentAndCleanup();

                        // 4. Conditions de victoire
                        checkEndGameConditions();

                        turn++;

                        System.out.println("\n[SIMULATION] Le temps s'écoule... (Prochain tour dans 5s)");
                        System.out.println("   (Tapez Entrée à tout moment pour prendre la main !)");
                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        break;
                    }
                } else {
                    try { Thread.sleep(500); } catch (InterruptedException e) { break; }
                }
            }
        });

        timeThread.start();

        Scanner inputListener = new Scanner(System.in);
        while (true) {
            inputListener.nextLine();
            if (!isPaused) {
                isPaused = true;
                System.out.println("\n🛑 TEMPS ARRÊTÉ ! À VOUS DE JOUER CHEF !");
                handlePlayerTurn();
                System.out.println("▶️ REMISE EN ROUTE DU TEMPS...");
                isPaused = false;
            }
        }
    }

    // --- 1. VIE QUOTIDIENNE (INTELLIGENCE ARTIFICIELLE) ---
    private void handleDailyLife() {
        System.out.println("\n🔨 --- VIE DU VILLAGE --- 🔨");

        for (Place place : places) {
            // On ne travaille pas et on ne mange pas tranquillement sur le champ de bataille
            if (place instanceof BattleField) continue;

            List<Character> population = place.getCharacterList();
            List<Food> pantry = place.getFoodList(); // Le garde-manger du lieu

            for (Character c : population) {
                if (c.isDead()) continue;

                // A. MANGER (Si faim < 50)
                // C'est ici qu'on utilise votre méthode eat() !
                if (c.getHunger().get() < 50) {
                    if (!pantry.isEmpty()) {
                        // On prend la première nourriture venue (ou on pourrait chercher la meilleure)
                        Food food = pantry.remove(0);
                        c.eat(food); // <-- Appel de VOTRE méthode avec toute sa logique (préférences, pas frais, etc.)
                    } else {
                        System.out.println("   " + c.getName() + " cherche à manger à " + place.getName() + " mais le stock est vide !");
                    }
                }

                // B. TRAVAILLER (Si c'est un IWorker et qu'il n'a pas trop faim)
                if (c instanceof IWorker && c.getHunger().get() > 20) {
                    ((IWorker) c).work();

                    // Récompense du travail : chance de produire de la nourriture ou potion
                    if (random.nextInt(100) < 20) { // 20% de chance de produire
                        if (c instanceof Druid) {
                            // Le druide soigne quelqu'un au hasard au lieu de créer de la nourriture
                            performRandomHeal(place, (Druid) c);
                        } else {
                            // Les autres (Forgeron, etc.) "produisent" (simulation simplifiée par création de nourriture)
                            Food produced = foodFactory.createFood(FoodType.WILD_BOAR); // Ils chassent ou produisent
                            place.addFood(produced);
                            System.out.println("   --> Le travail de " + c.getName() + " a rapporté : " + produced.getName());
                        }
                    }
                }
            }
        }
    }

    private void performRandomHeal(Place place, Druid druid) {
        // Le druide cherche le plus blessé
        Character target = null;
        int minHealth = 100;

        for (Character c : place.getCharacterList()) {
            if (!c.isDead() && c.getHealth().get() < minHealth) {
                minHealth = c.getHealth().get();
                target = c;
            }
        }

        if (target != null && target.getHealth().get() < 80) {
            System.out.println("   ✨ Le Druide " + druid.getName() + " soigne " + target.getName() + " !");
            target.heal(30);
        }
    }

    // --- 2. LOGIQUE DE COMBAT ---
    private void handleAdvancedBattles() {
        boolean fightingHappened = false;
        for (Place place : places) {
            if (place instanceof BattleField) {
                List<Character> fighters = place.getCharacterList();
                if (fighters.size() >= 2) {
                    if (!fightingHappened) {
                        System.out.println("\n⚔️ --- PHASE DE BATAILLE --- ⚔️");
                        fightingHappened = true;
                    }
                    System.out.println("   Bataille en cours sur : " + place.getName());

                    // Mélange pour aléatoire
                    List<Character> activeFighters = new ArrayList<>(fighters);
                    java.util.Collections.shuffle(activeFighters);

                    for (int i = 0; i < activeFighters.size() - 1; i += 2) {
                        Character c1 = activeFighters.get(i);
                        Character c2 = activeFighters.get(i+1);

                        if (c1.getFaction() != c2.getFaction() && !c1.isDead() && !c2.isDead()
                                && c1 instanceof IFighter && c2 instanceof IFighter) {
                            resolveDuel(c1, c2);
                        }
                    }
                }
            }
        }
    }

    private void resolveDuel(Character attacker, Character defender) {
        int atkPower = attacker.getStrength();
        // Bonus Potion
        if (attacker.getMagicPotion().get() > 0) {
            atkPower *= 3;
        }

        int defResistance = defender.getStamina() / 2;
        int damage = Math.max(1, atkPower - defResistance + random.nextInt(10));

        System.out.println("   ⚔️ " + attacker.getName() + " tape " + defender.getName() + " (-" + damage + " PV)");
        defender.takeDamage(damage);

        if (!defender.isDead()) {
            // Riposte
            int ripDamage = Math.max(1, (defender.getStrength()) - (attacker.getStamina() / 2));
            System.out.println("      ↪️ Riposte de " + defender.getName() + " (-" + ripDamage + " PV)");
            attacker.takeDamage(ripDamage);
        } else {
            System.out.println("      💀 " + defender.getName() + " est vaincu !");
        }
    }

    // --- 3. GESTION ENVIRONNEMENT ---
    private void updateEnvironmentAndCleanup() {
        for (Place place : places) {
            // A. Nettoyage des morts
            Iterator<Character> it = place.getCharacterList().iterator();
            while (it.hasNext()) {
                Character c = it.next();
                if (c.isDead()) {
                    it.remove();
                    continue;
                }

                // Digestion (Faim augmente)
                c.getHunger().add(-10); // On perd de la faim à chaque tour
                if (c.getHunger().get() <= 0) {
                    System.out.println("   ⚠️ " + c.getName() + " meurt de famine !");
                    c.takeDamage(1000);
                }

                // Dissipation potion
                if(c.getMagicPotion().get() > 0) c.getMagicPotion().add(-10);
            }

            // B. Pourrissement de la nourriture (Seulement si pas mangée)
            for (Food food : place.getFoodList()) {
                if (food.getFoodFreshness() == FoodFreshness.FRESH && random.nextInt(100) < 30) {
                    food.setFoodFreshness(FoodFreshness.FAIRLY_FRESH);
                } else if (food.getFoodFreshness() == FoodFreshness.FAIRLY_FRESH && random.nextInt(100) < 30) {
                    food.setFoodFreshness(FoodFreshness.NOT_FRESH);
                }
            }

            // C. Apparition spontanée (Nature)
            if (!(place instanceof BattleField) && random.nextInt(100) < 20) {
                List<Food> loot = stockFoodGenerator.generateInitialStock(1);
                if(!loot.isEmpty()) place.addFood(loot.get(0));
            }
        }
    }

    // --- 4. INTERACTIONS JOUEUR ---
    private void handlePlayerTurn() {
        System.out.println("\n👑 --- MENU CHEF DE CLAN --- 👑");

        List<SafePlace> safePlaces = places.stream()
                .filter(p -> p instanceof SafePlace)
                .map(p -> (SafePlace) p)
                .collect(Collectors.toList());

        System.out.println("Lieux sous votre commandement :");
        for (int i = 0; i < safePlaces.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + safePlaces.get(i).getName()
                    + " (Pop: " + safePlaces.get(i).getNumberOfCharacters()
                    + " | Vivres: " + safePlaces.get(i).getFoodList().size() + ")");
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
            System.out.println("1. Examiner (Voir détails persos & nourriture)");
            System.out.println("2. Envoyer des troupes au front");
            // Note: On a retiré "Nourrir tout le monde" car c'est automatique maintenant !
            // Mais on pourrait ajouter "Organiser un festin" qui force à manger même sans faim.
            System.out.println("3. Retour");

            int action = getIntInput(3);
            switch (action) {
                case 1:
                    place.displayPlaceInfo();
                    place.displayCharacterMinInfo();
                    place.displayFood();
                    break;
                case 2:
                    sendTroopsMenu(place);
                    break;
                case 3:
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

    private void checkEndGameConditions() {
        long gaulsAlive = places.stream().flatMap(p -> p.getCharacterList().stream()).filter(c -> c.getFaction() == Faction.GAULS).count();
        long romansAlive = places.stream().flatMap(p -> p.getCharacterList().stream()).filter(c -> c.getFaction() == Faction.ROMAN).count();

        if (gaulsAlive == 0) {
            System.out.println("\n[FIN] Victoire Romaine !");
            System.exit(0);
        } else if (romansAlive == 0) {
            System.out.println("\n[FIN] Victoire Gauloise !");
            System.exit(0);
        }
    }
}