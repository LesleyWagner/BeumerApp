package lesl.beumerapp.models;

import java.util.Arrays;
import java.util.List;

public class Chutes extends Element {
    public static final int totalAreas = 4; // amount of distinctive areas the element is divided in
    public static final String name = "chutes"; // name of the element
    private static final int filePosition = 1; // position/order of the element in the data file
    private static final int viewPosition = 0; // position of the element in tab view
    // area names, order important
    private final String areaName1 = "LOL014-030";
    private final String areaName2 = "LOL032-047";
    private final String areaName3 = "LOL187-202";
    private final String areaName4 = "LOL204-220";
    private final List<String> areaNames = Arrays.asList(areaName1, areaName2, areaName3, areaName4);

    public Chutes(List<Area> data) {
        super(name, filePosition, viewPosition, data);
    }

}
