package src.Food;

public class PotionFactory {

    // Une dose normale
    public static MagicPotion singleDose() {
        return new MagicPotion(1, 0, false, false);
    }

    // Une marmite (effet permanent)
    public static MagicPotion oneCauldron() {
        return new MagicPotion(0, 1, false, false);
    }

    // Deux marmites (statue)
    public static MagicPotion twoCauldrons() {
        return new MagicPotion(0, 2, false, false);
    }

    // Dose + lait de licorne (pouvoir duplication)
    public static MagicPotion unicornDose() {
        return new MagicPotion(1, 0, true, false);
    }

    // Marmite + poils d’Idéfix (métamorphose)
    public static MagicPotion metamorphosisCauldron() {
        return new MagicPotion(0, 1, false, true);
    }

    // Marmite complète avec tous les ingrédients
    public static MagicPotion fullLegendaryPotion() {
        return new MagicPotion(1, 1, true, true);
    }
}
