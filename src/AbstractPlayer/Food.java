package src.AbstractPlayer;

public class Food extends AbstractFood {

    private FoodType type;

    public Food(FoodType type) {
        super(type.name, type.defaultFresh);
        this.type = type;
    }

    @Override
    public boolean isDangerous() {
        // règle : poisson pas frais = dangereux
        if (type == FoodType.POISSON_PAS_FRAIS) return true;

        return false;
    }

    public FoodCategory getCategory() {
        return type.category;
    }
}

