package converter;

import fileio.writers.TxtWriter;
import model.Network;
import model.Show;
import fileio.readers.XmlReader;
import fileio.writers.JsonWriter;

import java.io.File;
import java.util.ArrayList;

public class ConvertFromXmlToJson implements Converter {
    private final String FILE_PATH = new File("").getAbsolutePath() + "\\src\\test\\java\\";

    @Override
    public void convert(String inputFile, String outputFile) {
        XmlReader reader = new XmlReader();
        ArrayList<Show> shows = reader.readFromFile(FILE_PATH + "input\\" + inputFile);

        if (shows != null) {
            ArrayList<Network> networks = new ArrayList<>();
            for (Show show : shows) {
                String networkName = show.getNetwork();
                int networkIndex = getNetworkIndex(networks, networkName);
                if (networkIndex == -1) {
                    Network network = new Network();
                    network.setName(networkName);
                    network.addShow(show);
                    networks.add(network);
                } else {
                    networks.get(networkIndex).addShow(show);
                }
            }

            JsonWriter writer = new JsonWriter();
            writer.writeToFile(networks, FILE_PATH + "output\\" + outputFile);

            TxtWriter txtWriter = new TxtWriter();
            txtWriter.writeToFile(shows, FILE_PATH + "output\\outputText.txt");
        }
    }

    private int getNetworkIndex(ArrayList<Network> networks, String networkName) {
        for (int i = 0; i < networks.size(); ++i) {
            if (networks.get(i).getName().equals(networkName)) {
                return i;
            }
        }
        return -1;
    }
}