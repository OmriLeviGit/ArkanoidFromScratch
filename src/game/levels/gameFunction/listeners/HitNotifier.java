// 206573289 Omri Levi


package game.levels.gameFunction.listeners;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add a listener to hit events.
     *
     * @param hl the hit Listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a listener from the list of listeners to hit events.
     *
     * @param hl the hit Listener
     */
    void removeHitListener(HitListener hl);
}