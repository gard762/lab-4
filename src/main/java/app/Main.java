package app;

import main.java.exceptions.FigureFileFormatException;
import exceptions.InvalidFigureParametrException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.FigureStorage;
import storage.SerializedFigureStorage;
import storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path inputPath;
        Path outputPath;

        if (args.length >= 2) {
            inputPath = Paths.get(args[0]);
            outputPath = Paths.get(args[1]);
        } else {
            System.out.print("Введите путь к входному текстовому файлу: ");
            inputPath = Paths.get(scanner.nextLine());
            System.out.print("Введите путь к выходному бинарному файлу: ");
            outputPath = Paths.get(scanner.nextLine());
        }

        FigureStorage textStorage = new TextFigureStorage();
        List<Figure> figures;
        try {
            figures = textStorage.read(inputPath);
            System.out.println("Успешно прочитано фигур: " + figures.size());
        } catch (IOException | ClassNotFoundException | FigureFileFormatException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Причина: " + e.getCause().getMessage());
            }
            return;
        }

        System.out.println("\n--- Список фигур ---");
        for (Figure figure : figures) {
            System.out.printf("Тип: %s, Площадь: %.2f, Периметр: %.2f%n",
                    figure.getClass().getSimpleName(), figure.area(), figure.perimetr());
        }

        System.out.println("\n--- Создание цилиндра ---");
        if (figures.isEmpty()) {
            System.out.println("Список фигур пуст. Создание цилиндра пропущено.");
        } else {
            Random random = new Random();
            Figure randomBase = figures.get(random.nextInt(figures.size()));
            System.out.println("Случайно выбрана основа: " + randomBase);

            try {
                System.out.print("Введите высоту цилиндра (или нажмите Enter для значения по умолчанию 10.0): ");
                String heightInput = scanner.nextLine().trim();
                double height = heightInput.isEmpty() ? 10.0 : Double.parseDouble(heightInput);

                Cylinder cylinder = new Cylinder(randomBase, height);
                System.out.printf("Объем цилиндра: %.2f%n", cylinder.volume());
            } catch (NumberFormatException e) {
                System.err.println("Некорректный ввод высоты. Используется значение по умолчанию 10.0.");
                try {
                    Cylinder cylinder = new Cylinder(randomBase, 10.0);
                    System.out.printf("Объем цилиндра: %.2f%n", cylinder.volume());
                } catch (InvalidFigureParametrException ex) {
                    System.err.println("Ошибка создания цилиндра: " + ex.getMessage());
                }
            } catch (InvalidFigureParametrException e) {
                System.err.println("Ошибка создания цилиндра: " + e.getMessage());
            }
        }

        System.out.println("\n--- Сохранение в бинарный файл ---");
        FigureStorage binaryStorage = new SerializedFigureStorage();
        try {
            binaryStorage.write(outputPath, figures);
            System.out.println("Данные успешно сохранены в " + outputPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }
}