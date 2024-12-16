//212067466 Roee Buchler
package Hit;
/**
 * The HitNotifier interface specifies the methods that should be implemented by any class
 * that wants to notify listeners of hit events.
 */
public interface HitNotifier {
    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);
    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
