package lesl.beumerapp.models;

import java.util.List;

public class Element implements Cloneable {
    public final String name;
    public final int filePosition;
    public final int viewPosition;
    private List<Area> areas; // sorted list of areas with frequency data

    public Element(String name, int filePosition, int viewPosition, List<Area> data) {
        this.name = name;
        this.filePosition = filePosition;
        this.viewPosition = viewPosition;
        this.areas = data;
    }

    /**
     * Gets sorted list of areas from the element with frequency data.
     * @return (List of Area) sorted list of areas from the element with frequency data.
     */
    public List<Area> getData() {
        return areas;
    }

    /**
     * Updates area inside areaData.
     * @param area (Area) must be an area inside areaData
     */
    public void update(Area area) {
        areas.set(area.filePosition, area);
    }

    /**
     * Sets frequency of all areas of this element to 0.
     */
    public void resetAreas() {
        for (Area area : areas) {
            areas.set(area.filePosition, area.reset());
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
