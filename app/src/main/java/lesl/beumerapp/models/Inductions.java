package lesl.beumerapp.models;

import java.util.Arrays;
import java.util.List;

public class Inductions extends Element {
    public static final int totalAreas = 13; // amount of distinctive areas the element is divided in
    public static final String name = "inductions"; // name of the element
    private static final int filePosition = -1; // position/order of the element in the data file, invalid for element type inductions
    private static final int viewPosition = 0; // position of the element in tab view

    public Inductions(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }
}
