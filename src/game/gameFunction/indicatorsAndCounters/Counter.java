// 206573289 Omri Levi


package game.gameFunction.indicatorsAndCounters;

/**
 * The type Counter.
 */
public class Counter {
    private int count = 0;

    /**
     * Increase the count by a given number.
     *
     * @param number the number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decrease a number from the current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Gets the current count.
     *
     * @return the count
     */
    public int getValue() {
        return count;
    }
}