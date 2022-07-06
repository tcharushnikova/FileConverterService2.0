package fileio.writers;

import model.Network;
import model.Show;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter implements Writer<Network> {
    @Override
    public void writeToFile(ArrayList<Network> networksArray, String filename) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            FileWriter writer = new FileWriter(filename);
            JsonObject networksObj = getNetworksObj(networksArray);
            writer.write(gson.toJson(networksObj));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("При записи в json-файл произошла ошибка!");
        }
    }

    private JsonObject getNetworksObj(ArrayList<Network> networksList) {
        JsonArray networks = new JsonArray();
        for (Network network : networksList) {
            JsonObject networkObj = new JsonObject();
            networkObj.addProperty("name", network.getName());
            networkObj.add("tvseries", getShowsObj(network.getShows()));

            JsonObject networksObjWrapper = new JsonObject();
            networksObjWrapper.add("network", networkObj);
            networks.add(networksObjWrapper);
        }

        JsonObject networksObj = new JsonObject();
        networksObj.add("networks", networks);
        return networksObj;
    }

    private JsonArray getShowsObj(ArrayList<Show> shows) {
        JsonArray showsObj = new JsonArray();
        for (Show show : shows) {
            JsonObject showObj = new JsonObject();
            showObj.addProperty("name", show.getName());
            showObj.addProperty("date", show.getDate());
            showObj.addProperty("country", show.getCountry());
            JsonArray genres = new JsonArray();
            for (String genre : show.getGenres()) {
                genres.add(genre);
            }
            showObj.add("genres", genres);
            showsObj.add(showObj);
        }
        return showsObj;
    }
}