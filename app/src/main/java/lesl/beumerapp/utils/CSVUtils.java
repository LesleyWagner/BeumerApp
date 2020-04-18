package lesl.beumerapp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }

    // Parses frequency data from csvfile into a map.
    public static Map<String, List<Integer>> parseFrequencies(BufferedReader reader) throws IOException {
        Map<String, List<Integer>> frequencies = new HashMap<String, List<Integer>>();
        String[] elementData;
        String line = reader.readLine();
        while (line != null) {
            elementData = line.split(",");
            String element = "";
            ArrayList<Integer> data = new ArrayList<>();
            for (String part : elementData) {
                if (part == elementData[0]) {
                    element = part;
                }
                else {
                    data.add(Integer.parseInt(part));
                }
            }
            frequencies.put(element, data);
            line = reader.readLine();
        }
        return frequencies;
    }
}
