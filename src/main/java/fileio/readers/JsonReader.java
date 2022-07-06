package fileio.readers;

import com.google.gson.JsonElement;
import model.Network;
import model.Show;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonReader implements Reader<Network> {
    @Override
    public ArrayList<Network> readFromFile(String fileName) {
        ArrayList<Network> networks = null;
        try {
            FileReader reader = new FileReader(fileName);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();
            networks = getNetworks(jsonObject);
        } catch (IOException e) {
            System.out.println("При считывании данных произошла ошибка!");
        }
        return networks;
    }

    private ArrayList<Network> getNetworks(JsonObject jsonObject) {
        ArrayList<Network> networks = new ArrayList<>();
        JsonArray networksArray = jsonObject.getAsJsonArray("networks");
        for (JsonElement networkObj : networksArray) {
            networks.add(getNetwork(networkObj));
        }
        return networks;
    }

    private Network getNetwork(JsonElement networkObj) {
        Network network = new Network();
        JsonObject networkJson = networkObj.getAsJsonObject().get("network").getAsJsonObject();
        network.setName(networkJson.get("name").getAsString());

        for (JsonElement showObj : networkJson.get("tvseries").getAsJsonArray()) {
            Show show = new Show();
            JsonObject showJson = showObj.getAsJsonObject();

            show.setName(showJson.get("name").getAsString());
            show.setDate(showJson.get("date").getAsString());
            show.setCountry(showJson.get("country").getAsString());
            show.setNetwork(networkJson.get("name").getAsString());
            ArrayList<String> genres = new ArrayList<>();
            for (JsonElement genreObj : showJson.get("genres").getAsJsonArray()) {
                genres.add(genreObj.getAsString());
            }
            show.setGenres(genres);
            network.addShow(show);
        }
        return network;
    }
}