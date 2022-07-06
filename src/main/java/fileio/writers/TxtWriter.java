package fileio.writers;

import model.Show;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TxtWriter implements Writer<Show> {
    @Override
    public void writeToFile(ArrayList<Show> showsList, String outputTxtFile) {
        try (FileWriter writer = new FileWriter(outputTxtFile)) {
            StringBuilder output;
            for (Show show : showsList) {
                output = new StringBuilder("\nНазвание: " + show.getName()
                        + "\nДата выхода: " + show.getDate()
                        + "\nСтрана: " + show.getCountry()
                        + "\nКанал: " + show.getNetwork()
                        + "\nЖанры: ");
                for (String genre : show.getGenres())
                    output.append(genre).append(" ");
                writer.write(output.toString());
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("При записи в текстовый файл произошла ошибка!");
        }
    }
}