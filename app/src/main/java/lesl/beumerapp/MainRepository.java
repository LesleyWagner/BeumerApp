package lesl.beumerapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lesl.beumerapp.models.AppData;
import lesl.beumerapp.models.Area;
import lesl.beumerapp.models.Element;

@SuppressWarnings("ALL")
public class MainRepository {
    private final Resources resources;
    private File fileDirectory;
    private final String packageName;
    private File csvFile; // frequencies.csv
    private final String fileHeader; // header of frequencies.csv
    private final Context application;

    public MainRepository(Context application) {
        this.resources = application.getResources();
        this.fileDirectory = application.getFilesDir();
        this.packageName = application.getPackageName();
        csvFile = new File(fileDirectory, resources.getString(R.string.fileName));
        fileHeader = resources.getString(R.string.fileHeader);
        this.application = application;
    }

    public List<Element> getFrequencies() {
        List<Element> frequencies = new ArrayList<>();
        if (csvFile.exists()) {
            frequencies = readData();
        }
        else {
            new CreateDataTask().execute(frequencies);
        }
        // sort by position in tab view
        Collections.sort(frequencies, new Comparator<Element>() {
            @Override
            public int compare(Element lhs, Element rhs) {
                return lhs.viewPosition - rhs.viewPosition;
            }
        });
        return frequencies;
    }

    public void saveData(List<Element> frequencies) {
        Collections.sort(frequencies, new Comparator<Element>() {
            @Override
            public int compare(Element lhs, Element rhs) {
                return lhs.filePosition - rhs.filePosition;
            }
        }); // sort by position in file
        writeData(frequencies);
    }

    /**
     * Writes frequency data to the file 'frequencies.csv'.
     * @param frequencies - ordered list of frequency data
     */
    private void writeData(List<Element> frequencies) {
        new SaveDataTask().execute(frequencies); // save data in background thread
    }

    /**
     * Reads frequency data from the file 'frequencies.csv'.
     * @return ordered list of elements with frequency data
     */
    private List<Element> readData() {
        List<Element> frequencies = new ArrayList<>(); // order important!

         new LoadDataTask().execute(frequencies); // load data in background thread

        return frequencies;
    }

    /**
     * Writes frequency data to the file 'frequencies.csv' in background thread.
     * 'frequencies' in doInBackground - ordered list of frequency data
     */
    private class SaveDataTask extends AsyncTask<List<Element>, Void, Void> {
        @Override
        protected Void doInBackground(List<Element>... frequencies) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
                writer.write(fileHeader);
                writer.newLine();
                for (Element element : frequencies[0]) {
                    // write new line for each area of the element
                    for (Area area : element.getData()) {
                        writer.write(area.name);
                        writer.write(",");
                        writer.write(String.valueOf(area.getFrequency()));
                        writer.newLine();
                    }
                }
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Reads frequency data from the file 'frequencies.csv' in a background thread.
     * 'frequencies' parameter in doInBackground must be an empty list.
     */
    private class LoadDataTask extends AsyncTask<List<Element>, Void, Void> {
        @Override
        protected Void doInBackground(List<Element>... frequencies) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                String header = reader.readLine();
                for (int i = 0; i < AppData.elementsSize; i++) {
                    AppData.ElementData elementNames = AppData.elements[i];
                    List<Area> data = new ArrayList<>();

                    for (int j = 0; j < elementNames.areaSize; j++) {
                        String[] dataString = reader.readLine().split(",");
                        data.add(new Area(elementNames.areaNames[j], Integer.parseInt(dataString[1]), j));
                    }
                    frequencies[0].add(new Element(elementNames.name, elementNames.filePosition, elementNames.viewPosition, data));
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Creates initial frequency data in a background thread.
     * Only called once in the application lifetime, when the data file doesn't exist yet.
     * After task execution write created data to file.
     */
    private class CreateDataTask extends AsyncTask<List<Element>, Void, List<Element>> {
        @Override
        protected List<Element> doInBackground(List<Element>... frequencies) {
            // initialise frequency data with all zeros
            for (int i = 0; i < AppData.elementsSize; i++) {
                AppData.ElementData elementNames = AppData.elements[i];
                List<Area> data = new ArrayList<>();

                for (int j = 0; j < elementNames.areaSize; j++) {
                    data.add(new Area(elementNames.areaNames[j], 0, j));
                }
                frequencies[0].add(new Element(elementNames.name, elementNames.filePosition, elementNames.viewPosition, data));
            }
            return frequencies[0];
        }

        @Override
        protected void onPostExecute(List<Element> frequencies) {
            // write created data to file
            writeData(frequencies);
        }
    }
}
