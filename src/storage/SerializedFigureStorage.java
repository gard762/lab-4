package storage;

import exceptions.FigureFileFormatException;
import geometry2d.Figure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SerializedFigureStorage implements FigureStorage{
    @Override
    @SuppressWarnings("unchecked")
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException, FigureFileFormatException{
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            Object obj = ois.readObject();
            if (!(obj instanceof List)){
                throw new FigureFileFormatException("Файл не содержит список фигур");
            }
            List<?> rawList = (List<?>) obj;

            for(Object item : rawList){
                if(!(item instanceof Figure)){
                    throw new FigureFileFormatException("Элемент списка не является фигурой");
                }
            }
            return (List<Figure>) rawList;
        }
    }

    public void write(Path path, List<Figure> figures) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(figures);
        }
    }
}