import converter.ConvertFromJsonToXml;
import converter.ConvertFromXmlToJson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название входного файла (с расширением): ");
        String inputFile = in.nextLine();
        System.out.print("Введите название выходного файла (без расширения): ");
        String outputFile = in.nextLine();

        if (inputFile.endsWith(".xml")) {
            outputFile += ".json";
            ConvertFromXmlToJson converter = new ConvertFromXmlToJson();
            converter.convert(inputFile, outputFile);
        } else if (inputFile.endsWith(".json")) {
            outputFile += ".xml";
            ConvertFromJsonToXml converter = new ConvertFromJsonToXml();
            converter.convert(inputFile, outputFile);
        } else
            System.out.println("Расширение файла не соответствует требованиям");
    }
}