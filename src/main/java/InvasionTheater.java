package src.main.java;

import src.main.java.characters.Character;
import src.main.java.characters.ClanChief;
import src.main.java.characters.IFighter;
import src.main.java.characters.IWorker;
import src.main.java.characters.gaulois.Druid;
import src.main.java.Enum.character.Faction;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;
import src.main.java.food.Food;
import src.main.java.food.FoodFactory;
import src.main.java.food.StockFood;
import src.main.java.place.BattleField;
import src.main.java.place.Place;
import src.main.java.place.SafePlace;
import src.main.java.items.Cauldron; // Assurez-vous d'avoir créé la classe Cauldron dans le package items

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
    private FoodFactory foodFactory;

    // Contrôle du thread de temps
    private volatile boolean isPaused = false;

    /**
     * InvasionTheater constructor
     * @param name
     * @param places
     * @param chiefs
     */
    public InvasionTheater(String name, List<Place> places, List<ClanChief> chiefs) {
        this.name = name;
        this.places = places;
        this.chiefs = chiefs;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.stockFoodGenerator = new StockFood();
        this.foodFactory = new FoodFactory();
    }

    /**
     * The run methode
     */
    public void run() {

        Thread timeThread = new Thread(() -> {
            int turn = 1;
            while (true) {
                if (!isPaused) {
                    try {
                        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
                        System.out.println("║   ANNÉE 50 AV. J.C. - SEMAINE " + turn + " - THÉÂTRE : " + this.name.toUpperCase() + "   ║");
                        System.out.println("╚════════════════════════════════════════════════════════════════╝");

                        // 1. La vie quotidienne (Manger, Travailler, Potion)
                        handleDailyLife();

                        // 2. Les Combats
                        handleAdvancedBattles();

                        // 3. Gestion de l'environnement
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

    /**
     * Daily life methode
     */
    // --- 1. VIE QUOTIDIENNE ---
    private void handleDailyLife() {
        System.out.println("\n🔨 --- VIE DU VILLAGE --- 🔨");

        for (Place place : places) {
            if (place instanceof BattleField) continue;

            List<Character> population = place.getCharacterList();
            List<Food> pantry = place.getFoodList();

            // Gestion Automatique de la Potion (Les druides vérifient la marmite)
            if (place instanceof SafePlace) {
                SafePlace safePlace = (SafePlace) place;
                Cauldron cauldron = safePlace.getCauldron();

                // Si la marmite est vide, on cherche un druide pour en faire
                if (cauldron != null && cauldron.isEmpty()) {
                    for (Character c : population) {
                        if (c instanceof Druid) {
                            // Le druide tente de faire la potion s'il travaille
                            ((Druid) c).brewPotion(place, cauldron);
                            break; // Un seul druide suffit par tour
                        }
                    }
                }
            }

            for (Character c : population) {
                if (c.isDead()) continue;

                // A. MANGER
                if (c.getHunger().get() < 50) {
                    if (!pantry.isEmpty()) {
                        Food food = pantry.remove(0);
                        c.eat(food);
                    }
                }

                // B. TRAVAILLER
                if (c instanceof IWorker && c.getHunger().get() > 20) {
                    ((IWorker) c).work();

                    if (random.nextInt(100) < 20) {
                        if (c instanceof Druid) {
                            performRandomHeal(place, (Druid) c);
                        } else {
                            Food produced = foodFactory.createFood(FoodType.WILD_BOAR);
                            place.addFood(produced);
                            System.out.println("   --> " + c.getName() + " a produit : " + produced.getName());
                        }
                    }
                }
            }
        }
    }


    /**
     * Heal character if his life is < 80
     * @param place
     * @param druid
     */
    private void performRandomHeal(Place place, Druid druid) {
        Character target = null;
        int minHealth = 100;
        for (Character c : place.getCharacterList()) {
            if (!c.isDead() && c.getHealth().get() < minHealth) {
                minHealth = c.getHealth().get();
                target = c;
            }
        }
        if (target != null && target.getHealth().get() < 80) {
            System.out.println("   ✨ Le Druide " + druid.getName() + " soigne " + target.getName());
            target.heal(30);
        }
    }

    /**
     * Battle management
     */
    // --- 2. COMBATS ---
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
                    System.out.println("   Bataille sur : " + place.getName());

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

    /**
     * Duel between two character
     * @param attacker
     * @param defender
     */
    private void resolveDuel(Character attacker, Character defender) {
        int atkPower = attacker.getStrength();

        // Bonus Potion (Si actif, force x3)
        // Note: isInvincible est géré dans takeDamage() du défenseur
        if (attacker.getMagicPotion().get() > 0) {
            atkPower *= 3;
            System.out.print("⚡ ");
        }

        int defResistance = defender.getStamina() / 2;
        int damage = Math.max(1, atkPower - defResistance + random.nextInt(10));

        System.out.println(attacker.getName() + " tape " + defender.getName() + " (-" + damage + " PV)");
        defender.takeDamage(damage);

        if (!defender.isDead()) {
            int ripPower = defender.getStrength();
            if (defender.getMagicPotion().get() > 0) ripPower *= 3;

            int ripDamage = Math.max(1, ripPower - (attacker.getStamina() / 2));
            System.out.println("      ↪️ Riposte de " + defender.getName() + " (-" + ripDamage + " PV)");
            attacker.takeDamage(ripDamage);
        } else {
            System.out.println("      💀 " + defender.getName() + " est vaincu !");
        }
    }

    /**
     * Update the environnement and clean
     */
    // --- 3. ENVIRONNEMENT ---
    private void updateEnvironmentAndCleanup() {
        for (Place place : places) {
            Iterator<Character> it = place.getCharacterList().iterator();
            // On retire un personnage si il est mort
            while (it.hasNext()) {
                Character c = it.next();
                if (c.isDead()) {
                    it.remove();
                    continue;
                }

                // Faim
                c.getHunger().add(-10);
                if (c.getHunger().get() <= 0) {
                    System.out.println("   ⚠️ " + c.getName() + " meurt de famine !");
                    c.takeDamage(1000);
                }

                // Dissipation potion (Appel de la méthode du Character)
                c.decreasePotionEffect();
            }

            // Pourrissement et Apparition nourriture...
            // (Code identique à la version précédente pour abréger)
            for (Food food : place.getFoodList()) {
                if (food.getFoodFreshness() == FoodFreshness.FRESH && random.nextInt(100) < 30) {
                    food.setFoodFreshness(FoodFreshness.FAIRLY_FRESH);
                } else if (food.getFoodFreshness() == FoodFreshness.FAIRLY_FRESH && random.nextInt(100) < 30) {
                    food.setFoodFreshness(FoodFreshness.NOT_FRESH);
                }
            }
            if (!(place instanceof BattleField) && random.nextInt(100) < 20) {
                List<Food> loot = stockFoodGenerator.generateInitialStock(1);
                if(!loot.isEmpty()) place.addFood(loot.getFirst());
            }
        }
    }

    /**
     * Player (User) interaction
     */
    // --- 4. INTERACTIONS JOUEUR (Chef de Clan) ---
    private void handlePlayerTurn() {
        System.out.println("\n👑 --- MENU CHEF DE CLAN --- 👑");

        List<SafePlace> safePlaces = places.stream()
                .filter(p -> p instanceof SafePlace)
                .map(p -> (SafePlace) p)
                .collect(Collectors.toList());

        System.out.println("Lieux sous commandement :");
        for (int i = 0; i < safePlaces.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + safePlaces.get(i).getName()
                    + " (Pop: " + safePlaces.get(i).getNumberOfCharacters() + ")");
        }
        System.out.println(" 0. Retour");

        int choice = getIntInput(safePlaces.size());
        if (choice == 0) return;

        SafePlace selectedPlace = safePlaces.get(choice - 1);
        managePlace(selectedPlace);
    }

    /**
     * Place management
     * @param place
     */
    private void managePlace(SafePlace place) {
        boolean back = false;
        while (!back) {
            Cauldron cauldron = place.getCauldron();
            int doses = (cauldron != null) ? cauldron.getDoses() : 0;

            System.out.println("\n--- GESTION : " + place.getName().toUpperCase() + " ---");
            System.out.println("1. Examiner le lieu");
            System.out.println("2. Envoyer des troupes au front");
            System.out.println("3. Demander au Druide de préparer la Potion (Stock Marmite: " + doses + ")");
            System.out.println("4. Distribuer la Potion Magique");
            System.out.println("5. Retour");

            int action = getIntInput(5);
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
                    // Ordre au druide [Cite: 181]
                    orderDruidToBrew(place);
                    break;
                case 4:
                    // Distribution [Cite: 186]
                    distributePotion(place);
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }

    /**
     * Order druid to brew magic potion
     * @param place
     */
    // Commande Chef -> Druide
    private void orderDruidToBrew(SafePlace place) {
        Cauldron cauldron = place.getCauldron();
        if (cauldron == null) {
            System.out.println("Il n'y a pas de marmite ici !");
            return;
        }

        // Trouver un druide
        Druid druid = null;
        for (Character c : place.getCharacterList()) {
            if (c instanceof Druid) {
                druid = (Druid) c;
                break;
            }
        }

        if (druid != null) {
            // Le druide exécute l'ordre
            druid.brewPotion(place, cauldron);
        } else {
            System.out.println("Aucun druide disponible pour cet ordre !");
        }
    }

    /**
     * Clan chief distribut potion
     * @param place
     */
    // Commande Chef -> Troupes
    private void distributePotion(SafePlace place) {
        Cauldron cauldron = place.getCauldron();
        if (cauldron == null || cauldron.isEmpty()) {
            System.out.println("La marmite est vide ou inexistante !");
            return;
        }

        System.out.println("Distribution de la potion en cours...");
        int count = 0;
        for (Character c : place.getCharacterList()) {
            // Seuls les combattants boivent, et seulement s'ils n'en ont pas déjà
            if (c instanceof IFighter && c.getMagicPotion().get() == 0) {
                if (cauldron.takeDose()) {
                    c.drinkMagicPotion(50); // +50 niveau potion
                    // Appliquer effets spéciaux (Licorne/Idéfix) si la marmite en a
                    c.applySpecialPotionEffect(cauldron.hasUnicornMilk(), cauldron.hasIdefixHair());
                    count++;
                } else {
                    System.out.println("Plus de potion !");
                    break;
                }
            }
        }
        System.out.println(count + " guerriers ont bu la potion !");
    }

    /**
     * Send character in battlefield
     * @param source
     */
    private void sendTroopsMenu(SafePlace source) {
        List<BattleField> battleFields = places.stream()
                .filter(p -> p instanceof BattleField)
                .map(p -> (BattleField) p)
                .collect(Collectors.toList());

        if (battleFields.isEmpty()) {
            System.out.println("Aucun champ de bataille disponible !");
            return;
        }

        System.out.println("Vers quel champ de bataille ? (1-" + battleFields.size() + ")");
        int bfIndex = getIntInput(battleFields.size());
        if (bfIndex == 0) return;

        System.out.println("Combien de soldats ? (Max " + source.getNumberOfCharacters() + ")");
        int count = getIntInput(source.getNumberOfCharacters());

        if (count > 0) source.transferCharacter(count, battleFields.get(bfIndex - 1));
    }

    /**
     * get Player (User) choose by his input
     * @param max
     * @return int
     */
    private int getIntInput(int max) {
        System.out.print("Choix > ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Choix > ");
        }
        int val = scanner.nextInt();
        return Math.max(0, Math.min(val, max));
    }

    /**
     * Check if everybody dead
     */
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