package fileio.readers;

import model.Show;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlReader implements Reader<Show> {
    @Override
    public ArrayList<Show> readFromFile(String fileName) {
        ArrayList<Show> shows = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            shows = getShows(document);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("При считывании данных произошла ошибка!");
        }
        return shows;
    }

    private ArrayList<Show> getShows(Document document) {
        ArrayList<Show> shows = new ArrayList<>();
        NodeList showsNodeList = document.getDocumentElement().getElementsByTagName("show");

        for (int i = 0; i < showsNodeList.getLength(); ++i) {
            shows.add(getShow(showsNodeList.item(i)));
        }
        return shows;
    }

    private Show getShow(Node showNode) {
        Show show = new Show();
        NodeList showChildNodes = showNode.getChildNodes();

        for (int i = 0; i < showChildNodes.getLength(); ++i) {
            Node showChildNode = showChildNodes.item(i);
            if (showChildNode.getNodeType() != Node.TEXT_NODE) {
                switch (showChildNode.getNodeName()) {
                    case "name" -> show.setName(showChildNode.getTextContent());
                    case "date" -> show.setDate(showChildNode.getTextContent());
                    case "country" -> show.setCountry(showChildNode.getTextContent());
                    case "network" -> show.setNetwork(showChildNode.getTextContent());
                    case "genres" -> {
                        ArrayList<String> genres = new ArrayList<>();
                        NodeList genresNodeList = showChildNode.getChildNodes();
                        for (int j = 0; j < genresNodeList.getLength(); ++j) {
                            Node genre = genresNodeList.item(j);
                            if (genre.getNodeType() != Node.TEXT_NODE) {
                                genres.add(genre.getTextContent());
                            }
                        }
                        show.setGenres(genres);
                    }
                    default -> System.out.println("Ошибка в структуре XML-файла");
                }
            }
        }
        return show;
    }
}