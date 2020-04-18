package lesl.beumerapp.models;

public final class AppData {
    private static String[] othersAreaNames = new String[] {"BXT001-101", "HOS001", "SLS001", "HLY001-101"};
    private static String[] chutesAreaNames = new String[] {"LOL014-030", "LOL032-047", "LOL187-202", "LOL204-220"};
    private static String[] depsAreaNames = new String[] {"DEP064-088", "DEP091-117", "DEP119-128", "DEP130-151", "DEP155-170"};
    private static String[] unloadsAreaNames = new String[] {"UNL006-011", "UNL225-230", "UNL173-184"};
    private static String[] nclAreaNames = new String[] {"NCL006", "NCL173", "NCL230"};
    private static ElementData others = new ElementData("others", 0, 4, othersAreaNames, 4);
    private static ElementData chutes = new ElementData("chutes", 1, 0, chutesAreaNames, 4);
    private static ElementData deps = new ElementData("deps", 2, 3, depsAreaNames, 5);
    private static ElementData unloads = new ElementData("unloads", 3, 1, unloadsAreaNames, 3);
    private static ElementData ncl = new ElementData("ncl", 4, 2, nclAreaNames, 3);
    public static final ElementData[] elements = new ElementData[] {others, chutes, deps, unloads, ncl};
    public static final int elementsSize = 5;

    public static class ElementData {
        public final String name;
        public final int filePosition;
        public final int viewPosition;
        public final String[] areaNames;
        public final int areaSize;

        public ElementData(String name, int filePosition, int viewPosition, String[] areaNames, int areaSize) {
            this.name = name;
            this.filePosition = filePosition;
            this.viewPosition = viewPosition;
            this.areaNames = areaNames;
            this.areaSize = areaSize;
        }
    }
}
