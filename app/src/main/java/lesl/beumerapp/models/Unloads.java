package lesl.beumerapp.models;

import java.util.Arrays;
import java.util.List;

public class Unloads extends Element {
    public static final int totalAreas = 3; // amount of distinctive areas the element is divided in
    public static final String name = "unloads"; // name of the element
    private static final int filePosition = 3; // position/order of the element in the data file
    private static final int viewPosition = 1; // position of the element in tab view
    // area names, order important
    private final String areaName1 = "UNL006-011";
    private final String areaName2 = "UNL225-230";
    private final String areaName3 = "UNL173-184";
    private final List<String> areaNames = Arrays.asList(areaName1, areaName2, areaName3);

    public Unloads(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }
}
