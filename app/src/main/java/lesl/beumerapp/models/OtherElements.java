package lesl.beumerapp.models;

import java.util.Arrays;
import java.util.List;

public class OtherElements extends Element {
    public static final int totalAreas = 4; // amount of distinctive areas the element is divided in
    public static final String name = "others"; // name of the element
    private static final int filePosition = 0; // position/order of the element in the data file
    private static final int viewPosition = 4; // position of the element in tab view
    // area names, order important
    private final String areaName1 = "BXT001";
    private final String areaName2= "HOS001";
    private final String areaName3 = "SLS001";
    private final String areaName4 = "HLY001-101";
    private final List<String> areaNames = Arrays.asList(areaName1, areaName2, areaName3, areaName4);

    public OtherElements(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }
}
