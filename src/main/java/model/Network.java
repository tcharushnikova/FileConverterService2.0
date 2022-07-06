package model;

import java.util.ArrayList;

public class Network {
    private String name;
    private ArrayList<Show> shows;

    public Network() {
        name = null;
        shows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }
}