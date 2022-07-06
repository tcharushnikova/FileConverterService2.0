package fileio.writers;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

public interface Writer<T> {
    void writeToFile(ArrayList<T> array, String filename) throws IOException, ParserConfigurationException;
}