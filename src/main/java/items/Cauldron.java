package src.main.java.items;

public class Cauldron {
    private int doses;
    private final int maxDoses;

    // Propriétés spéciales de la potion
    private boolean hasUnicornMilk = false;
    private boolean hasIdefixHair = false;

    /**
     * Cauldron constructor
     * @param maxDoses
     */
    public Cauldron(int maxDoses) {
        this.doses = 0;
        this.maxDoses = maxDoses;
    }

    /**
     * Filling the cauldron
     * @param unicornMilk
     * @param idefixHair
     */
    public void fill(boolean unicornMilk, boolean idefixHair) {
        this.doses = this.maxDoses;
        this.hasUnicornMilk = unicornMilk;
        this.hasIdefixHair = idefixHair;
        System.out.println("   ✨ La marmite de potion est prête ! (" + doses + " doses)");
    }

    /**
     * Take doses
     * @return boolean
     */
    public boolean takeDose() {
        if (doses > 0) {
            doses--;
            return true;
        }
        return false;
    }

    public int getDoses() { return doses; }

    /// Check if cauldron is empty
    public boolean isEmpty() { return doses <= 0; }
    /// Check if there is unicorn milk
    public boolean hasUnicornMilk() { return hasUnicornMilk; }
    /// Check if there is idefix hair
    public boolean hasIdefixHair() { return hasIdefixHair; }
}
