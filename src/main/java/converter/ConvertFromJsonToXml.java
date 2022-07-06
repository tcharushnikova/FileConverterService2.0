package converter;

import fileio.writers.TxtWriter;
import model.Network;
import model.Show;
import fileio.readers.JsonReader;
import fileio.writers.XmlWriter;

import java.io.File;
import java.util.ArrayList;

public class ConvertFromJsonToXml implements Converter {
    private final String FILE_PATH = new File("").getAbsolutePath() + "\\src\\test\\java\\";

    @Override
    public void convert(String inputFile, String outputFile) {
        JsonReader reader = new JsonReader();
        ArrayList<Network> networks = reader.readFromFile(FILE_PATH + "input\\" + inputFile);

        if (networks != null) {
            ArrayList<Show> shows = new ArrayList<>();
            for (Network network : networks) {
                shows.addAll(network.getShows());
            }

            XmlWriter writer = new XmlWriter();
            writer.writeToFile(shows, FILE_PATH + "output\\" + outputFile);

            TxtWriter txtWriter = new TxtWriter();
            txtWriter.writeToFile(shows, FILE_PATH + "output\\outputText.txt");
        }
    }
}