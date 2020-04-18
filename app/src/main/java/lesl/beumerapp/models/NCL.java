package lesl.beumerapp.models;

import java.util.Arrays;
import java.util.List;

public class NCL extends Element {
    public static final int totalAreas = 3; // amount of distinctive areas the element is divided in
    public static final String name = "ncl"; // name of the element
    private static final int filePosition = 4; // position/order of the element in the data file
    private static final int viewPosition = 2; // position of the element in tab view
    // area names
    private final String areaName1 = "NCL006";
    private final String areaName2 = "NCL173";
    private final String areaName3 = "NCL230";
    private final List<String> areaNames = Arrays.asList(areaName1, areaName2, areaName3);

    public NCL(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }
}
