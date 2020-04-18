package lesl.beumerapp.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Represents a specific area of an element. Immutable datatype.
 * Holds frequency data for a specific area of an element.
 * Frequency has initial value of 0.
 * Returns a copy of this Area in every method in which the frequency is updated.
 */
public class Area {
    public final String name;
    private int frequency; // frequency of corrected errors
    public final int filePosition;

    public Area(String name, int value, int filePosition) {
        this.name = name;
        this.frequency = value;
        this.filePosition = filePosition;
    }

    /**
     * Gets frequency.
     * @return (int) frequency of corrected errors
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Increments frequency.
     * @return new Area instance with updated frequency.
     */
    public Area increment() {
        return new Area(name, frequency+1, filePosition);
    }

    /**
     * Decrements frequency if the value is bigger than 0, else leaves frequency as is.
     * @return new Area instance with updated frequency.
     */
    public Area decrement() {
        if (frequency > 0) {
            frequency -= 1;
        }
        return new Area(name, frequency, filePosition);
    }

    /**
     * Sets frequency of this area to 0.
     * @return new Area instance with updated frequency.
     */
    public Area reset() {
        return new Area(name, 0, filePosition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Area other = (Area)obj;
        return this.filePosition == other.filePosition && this.frequency == other.getFrequency()
                && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return filePosition;
    }
}
