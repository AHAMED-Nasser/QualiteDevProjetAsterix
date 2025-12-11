package src.main.java;

public class Statistics {
    private int value;
    private final int min;
    private int max;

    /**
     * Statistics constructor
     * @param value
     * @param min
     * @param max
     */
    public Statistics(int value, int min, int max) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.value = clamp(this.value);
    }

    /**
     * Clamp the value on the max.
     * Cannot go above the max value
     * @param value
     * @return
     */
    public int clamp(int value) {
        return Math.max(this.min, Math.min(value, this.max));
    }

    /**
     * Add current value not
     * @param amount
     */
    public void add(int amount) {
        this.value = this.clamp(this.value + amount);
    }

    /**
     * Increase max value
     * @param amount
     */
    public void increaseMax(int amount) {
        this.max += amount;
        this.value = this.clamp(this.value);
    }

    public void setMax(int max) {
        this.max = max;
        this.value = this.clamp(this.value);
    }

    public int get() { return this.value; }
    public int getMax() { return this.max; }
}
