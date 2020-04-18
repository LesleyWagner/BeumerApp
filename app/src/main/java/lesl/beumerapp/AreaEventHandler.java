package lesl.beumerapp;

import lesl.beumerapp.models.Area;

public interface AreaEventHandler {
    /**
     * Increments frequency of area on increment button click.
     * @param area - associated area
     */
    public void onIncrementClick(Area area);

    /**
     * Decrements frequency of area on decrement button click.
     * @param area - associated area
     */
    public void onDecrementClick(Area area);
}
