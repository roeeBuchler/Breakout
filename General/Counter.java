//212067466 Roee Buchler

package General;

/**
 * The Counter class provides methods to increase, decrease, and retrieve a count value.
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter object with an initial count value.
     *
     * @param num the initial count value
     */
    public Counter(int num) {
        count = num;
    }

    /**
     * Adds a specified number to the current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        count = count + number;
    }

    /**
     * Subtracts a specified number from the current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        count = count - number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}