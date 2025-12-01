package src.AbstractPlayer;
import src.AbstractPlayer.FoodCategory;
import javax.xml.namespace.QName;

public enum FoodType {
    SANGLIER("Sanglier", FoodCategory.MEAT, true),
    POISSON_FRAIS("Poisson passablement frais", FoodCategory.FISH, true),
    POISSON_PAS_FRAIS("Poisson pas frais", FoodCategory.FISH, false),
    GUI("Gui", FoodCategory.VEGETABLE, true),
    HOMARD("Homard", FoodCategory.FISH, true),
    FRAISES("Fraises", FoodCategory.VEGETABLE, true),
    CAROTTES("Carottes", FoodCategory.VEGETABLE, true),
    SEL("Sel", FoodCategory.OTHER, true),
    TREFLE_FRAIS("Trèfle frais", FoodCategory.VEGETABLE, true),
    TREFLE_PAS_FRAIS("Trèfle pas frais", FoodCategory.VEGETABLE, false),
    HUILE_DE_ROCHE("Huile de roche", FoodCategory.OTHER, true),
    JUS_BETTERAVE("Jus de betterave", FoodCategory.DRINK, true),
    MIEL("Miel", FoodCategory.MAGIC, true),
    VIN("Vin", FoodCategory.DRINK, true),
    HYDROMEL("Hydromel", FoodCategory.DRINK, true),
    LAIT_LICORNE("Lait de licorne à deux têtes", FoodCategory.MAGIC, true),
    POILS_IDEFIX("Poils d’Idéfix", FoodCategory.MAGIC, true),
    INGREDIENT_SECRET("Ingrédient secret", FoodCategory.MAGIC, true);

    public final String name;
    public final FoodCategory category;
    public final boolean defaultFresh;

    FoodType(String name, FoodCategory category, boolean defaultFresh) {
        this.name = name;
        this.category = category;
        this.defaultFresh = defaultFresh;
    }
}
