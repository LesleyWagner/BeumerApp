package lesl.beumerapp;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lesl.beumerapp.models.Area;
import lesl.beumerapp.models.Chutes;
import lesl.beumerapp.models.Deps;
import lesl.beumerapp.models.Element;
import lesl.beumerapp.models.NCL;
import lesl.beumerapp.models.OtherElements;
import lesl.beumerapp.models.Unloads;

public class MainViewModel extends AndroidViewModel {

    /**
     * Sorted unmodifiable list of elements in the beumer sortation system with frequency data,
     * ordered for presentation in tab view.
     */
    private List<Element> frequencies;

    private Element currentElement; // Current element in view in elementfragment.
    private MutableLiveData<List<Area>> currentAreas = new MutableLiveData<>(); // Livedata observable of list of areas from current element

    // The repository is used for handling data retrieval and storage.
    private MainRepository repository;

    public MainViewModel(Application application) {
        super(application);
        repository = new MainRepository(application);
        frequencies = repository.getFrequencies();
        // debug
        for (Element element : frequencies) {
            Log.d("element name", element.name);
        }
    }

    /**
     * Gets element by index in this.frequencies
     * @param index - index of the element in 'frequencies' list.
     * @return element with the given index
     */
    public Element getElement(int index) {
        return frequencies.get(index);
    }

    // Returns unmodifiable list 'frequencies'.
    public List<Element> getFrequencies() {
        return Collections.unmodifiableList(frequencies);
    }

    /**
     * Get name of an element by index.
     * @param index - index of the element in this.frequencies
     * @return name of the element
     */
    public String getElementName(int index) {
        return frequencies.get(index).name;
    }

    /**
     * Get copy of current element
     * @return shallow copy of current element
     */
    public Element getCurrentElement() {
        Element element = null;
        try {
            element = (Element)currentElement.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return element;
    }

    /**
     * Get LiveData wrapper of current areas
     * @return LiveData wrapper of areas from current element
     */
    public LiveData<List<Area>> getCurrentAreas() {
        return currentAreas;
    }

    /**
     * Sets new current element
     * @param position - position of current element inside the 'frequencies' list
     */
    public void setCurrentElement(int position) {
        currentElement = frequencies.get(position);
        currentAreas.setValue(currentElement.getData());
        Log.d("current element", position + ", " + currentElement.name);
    }

    /**
     * Increments frequency of area.
     *
     * @param area - area from current element of which the frequency is updated.
     */
    public void increment(Area area) {
        currentElement.update(area.increment());
        currentAreas.setValue(currentElement.getData());
    }

    /**
     * Decrements frequency of area.
     *
     * @param area - area from current element of which the frequency is updated.
     */
    public void decrement(Area area) {
        currentElement.update(area.decrement());
        currentAreas.setValue(currentElement.getData());
    }

    /**
     * Resets all data in frequencies to 0.
     */
    public void resetFrequencies() {
        for (Element element : frequencies) {
            element.resetAreas();
        }
        currentAreas.setValue(currentElement.getData());
    }

    /**
     * Called when activity is paused. Save data to persistent storage.
     */
    public void persistData() {
        repository.saveData(frequencies);
    }
}
