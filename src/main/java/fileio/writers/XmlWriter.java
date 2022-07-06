package fileio.writers;

import model.Show;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlWriter implements Writer<Show> {
    @Override
    public void writeToFile(ArrayList<Show> showsList, String filename) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            appendChildrenToDocument(document, showsList);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filename));
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("При записи в xml-файл произошла ошибка!");
        }
    }

    private void appendChildrenToDocument(Document document, ArrayList<Show> showsList) {
        Element shows = document.createElement("tvseries");
        document.appendChild(shows);

        for (int i = 0; i < showsList.size(); ++i) {
            Element show = document.createElement("show");
            show.setAttribute("id", String.valueOf(i));
            fillShow(document, show, showsList.get(i));
            shows.appendChild(show);
        }
    }

    private void fillShow(Document document, Element show, Show curShow) {
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(curShow.getName()));
        show.appendChild(name);

        Element date = document.createElement("date");
        date.appendChild(document.createTextNode(curShow.getDate()));
        show.appendChild(date);

        Element country = document.createElement("country");
        country.appendChild(document.createTextNode(curShow.getCountry()));
        show.appendChild(country);

        Element network = document.createElement("network");
        network.appendChild(document.createTextNode(curShow.getNetwork()));
        show.appendChild(network);

        Element genres = document.createElement("genres");
        for (String genreStr : curShow.getGenres()) {
            Element genre = document.createElement("genre");
            genre.appendChild(document.createTextNode(genreStr));
            genres.appendChild(genre);
        }
        show.appendChild(genres);
    }
}