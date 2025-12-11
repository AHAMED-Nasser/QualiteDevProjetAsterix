package src.main.java.items;

public class Cauldron {
    private int doses;
    private final int maxDoses;

    // Propriétés spéciales de la potion
    private boolean hasUnicornMilk = false;
    private boolean hasIdefixHair = false;

    public Cauldron(int maxDoses) {
        this.doses = 0;
        this.maxDoses = maxDoses;
    }

    public void fill(boolean unicornMilk, boolean idefixHair) {
        this.doses = this.maxDoses;
        this.hasUnicornMilk = unicornMilk;
        this.hasIdefixHair = idefixHair;
        System.out.println("   ✨ La marmite de potion est prête ! (" + doses + " doses)");
    }

    public boolean takeDose() {
        if (doses > 0) {
            doses--;
            return true;
        }
        return false;
    }

    public int getDoses() {
        return doses;
    }

    public boolean isEmpty() { return doses <= 0; }
    public boolean hasUnicornMilk() { return hasUnicornMilk; }
    public boolean hasIdefixHair() { return hasIdefixHair; }
}
