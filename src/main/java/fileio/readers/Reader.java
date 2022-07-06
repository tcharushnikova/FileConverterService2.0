package fileio.readers;

import java.util.ArrayList;

public interface Reader<T> {
    ArrayList<T> readFromFile(String filename);
}