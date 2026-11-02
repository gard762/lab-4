package storage;

import main.java.exceptions.FigureFileFormatException;
import exceptions.InvalidFigureParametrException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFigureStorage implements FigureStorage{
    @Override
    public List<Figure> read(Path path) throws IOException, FigureFileFormatException {
        List<Figure> figures = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null){
                lineNumber++;
                line = line.trim();
                if(line.isEmpty())
                    continue;
                try{
                    String[] parts = line.split("\\s+");
                    String type = parts[0].toLowerCase();
                    Figure figure;
                    switch (type) {
                        case "circle":
                            if (parts.length != 2) throw new FigureFileFormatException("Круг должен иметь 1 параметр");
                            double radius = Double.parseDouble(parts[1]);
                            figure = new Circle(radius);
                            break;
                        case "rectangle":
                            if(parts.length != 3) throw new FigureFileFormatException("Прямоугольник должен иметь 2 параметра");
                            double width = Double.parseDouble(parts[1]);
                            double height = Double.parseDouble(parts[2]);
                            figure = new Rectangle(width, height);
                            break;
                        default:
                            throw new FigureFileFormatException("Неизвестный тип фигуры");
                    }
                    figures.add(figure);
                } catch (NumberFormatException | InvalidFigureParametrException | FigureFileFormatException e){
                    throw new FigureFileFormatException("Ошибка в строке " + lineNumber + ": " + e.getMessage(), e);
                }
            }
        }
        return figures;
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for(Figure figure : figures){
                if(figure instanceof Circle c){
                    writer.write("circle" + c.getRadius());
                } else if (figure instanceof Rectangle r){
                    writer.write("rectangle" + r.getWidth() + " " + r.getHeight());
                } else {
                    throw new IOException("Неизвестный формат фигуры для записи.");
                }
                writer.newLine();
            }
        }
    }
}