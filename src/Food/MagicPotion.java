package src.Food;

public class MagicPotion {

    // Number of doses in the potion
    private int doses;

    // Number of cauldrons
    private int cauldrons;

    // Optional magical ingredients
    private final boolean twoHeadedUnicornMilk; // adds duplication power
    private final boolean idefixHair;// adds metamorphosis power

    // Constructor
    public MagicPotion(int doses, int cauldrons, boolean twoHeadedUnicornMilk, boolean idefixHair) {
        this.doses = doses;
        this.cauldrons = cauldrons;
        this.twoHeadedUnicornMilk = twoHeadedUnicornMilk;
        this.idefixHair = idefixHair;
    }

    // Getters
    public int getDoses() {
        return doses;
    }

    public int getCauldrons() {
        return cauldrons;
    }

    public boolean hasTwoHeadedUnicornMilk() {
        return twoHeadedUnicornMilk;
    }

    public boolean hasIdefixHair() {
        return idefixHair;
    }

    // Stats MagicPotion
    @Override
    public String toString() {
        return "MagicPotion {" +
                "doses=" + doses +
                ", cauldrons=" + cauldrons +
                ", unicornMilk=" + twoHeadedUnicornMilk +
                ", idefixHair=" + idefixHair +
                '}';
    }
}
