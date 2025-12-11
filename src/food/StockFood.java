package src.food;

import src.Enum.food.FoodType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

// La classe StockFood est responsable de la création du stock de nourriture initial.
public class StockFood {

    private final FoodFactory factory = new FoodFactory();
    private final Random random = new Random();

    // Définition des probabilités de génération pour chaque type de nourriture
    // (Les valeurs sont des poids, pas des pourcentages)
    private static final Map<FoodType, Integer> FOOD_WEIGHTS = Map.ofEntries(
            entry(FoodType.WILD_BOAR, 30),             // Le Sanglier est très fréquent
            entry(FoodType.FAIRLY_FRESH_FISH, 20),     // Le Poisson frais est fréquent
            entry(FoodType.NOT_FRESH_FISH, 15),        // Le Poisson pas frais aussi! (pour Ordralphabétix)
            entry(FoodType.MISTLETOE, 10),             // Gui (important pour Panoramix)
            entry(FoodType.LOBSTER, 5),                // Homard (plus rare/luxueux)
            entry(FoodType.STRAWBERRY, 10),            // Fraises
            entry(FoodType.CARROT, 10),                // Carottes
            entry(FoodType.SALT, 5),                   // Sel
            entry(FoodType.FOUR_LEAF_CLOVER, 3),       // Trèfle à 4 feuilles (rare)
            entry(FoodType.ROCK_OIL, 2),               // Huile de roche (ingrédient bizarre)
            entry(FoodType.BEET_JUICE, 5),             // Jus de betterave
            entry(FoodType.HONEY, 10),                 // Miel
            entry(FoodType.WINE, 7),                   // Vin
            entry(FoodType.MEAD, 7),                   // Hydromel
            entry(FoodType.UNICOR_MILK, 1),            // Lait de licorne (très rare)
            entry(FoodType.IDEFIX_HAIR, 1),            // Poils d'Idéfix (très rare)
            entry(FoodType.SECRET_INGREDIENT, 1)       // Ingrédient secret (très rare et puissant)
    );

    // Liste de tous les types de nourriture pour la sélection aléatoire
    private final List<FoodType> weightedFoodList = new ArrayList<>();

    public StockFood() {
        // Initialiser la liste pondérée pour la sélection aléatoire rapide
        FOOD_WEIGHTS.forEach((type, weight) -> {
            for (int i = 0; i < weight; i++) {
                weightedFoodList.add(type);
            }
        });
    }

    /**
     * Sélectionne un type de nourriture aléatoirement en utilisant les poids définis.
     * Les types avec un poids plus élevé (comme WILD_BOAR) ont plus de chances d'être choisis.
     *
     * @return Un FoodType sélectionné aléatoirement.
     */
    private FoodType getRandomWeightedFoodType() {
        int index = random.nextInt(weightedFoodList.size());
        return weightedFoodList.get(index);
    }

    /**
     * Génère un stock initial d'aliments pour la simulation.
     *
     * @param totalItems Le nombre total d'articles à générer.
     * @return La liste complète du stock de nourriture.
     */
    public List<Food> generateInitialStock(int totalItems) {
        List<Food> stock = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            FoodType type = getRandomWeightedFoodType();
            stock.add(factory.createFood(type));
        }
        return stock;
    }
}
