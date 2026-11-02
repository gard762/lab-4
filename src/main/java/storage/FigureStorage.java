package storage;

import main.java.exceptions.FigureFileFormatException;
import geometry2d.Figure;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

public interface FigureStorage {
    List<Figure> read(Path path) throws IOException, ClassNotFoundException, FigureFileFormatException;
    void write(Path path, List<Figure> figures) throws IOException;
}