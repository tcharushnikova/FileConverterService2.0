package converter;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

public interface Converter {
    void convert(String inputFile, String outputFile) throws IOException, ParserConfigurationException;
}