package lesl.beumerapp.models;

import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class Deps extends Element {
    public static final int totalAreas = 5; // amount of distinctive areas the element is divided in
    public static final String name = "deps"; // name of the element
    private static final int filePosition = 2; // position/order of the element in the data file
    private static final int viewPosition = 3; // position of the element in tab view
    // area names, order important
    private final String areaName1 = "DEP064-088";
    private final String areaName2 = "DEP091-117";
    private final String areaName3 = "DEP119-128";
    private final String areaName4 = "DEP130-151";
    private final String areaName5 = "DEP155-170";
    private final List<String> areaNames = Arrays.asList(areaName1, areaName2, areaName3, areaName4, areaName5);

    public Deps(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }
}
