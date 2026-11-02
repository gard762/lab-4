package geometry2d;

import exceptions.InvalidFigureParametrException;

import java.io.Serializable;

public class Rectangle implements Figure, Serializable {
    private static final long serialVersionUID = 1L;
    private final double width;
    private final double height;

    public Rectangle(double width, double height) throws InvalidFigureParametrException{
        if(width <= 0 || height <= 0 || Double.isInfinite(width) || Double.isInfinite(height) || Double.isNaN(width) || Double.isNaN(height)){
            throw new InvalidFigureParametrException("Стороны прямоугольника должны быть конечными положительными числами.");
        }

        this.width = width;
        this.height = height;
    }

    @Override
    public double area(){
        return width * height;
    }

    @Override
    public double perimetr(){
        return 2 * (width + height);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString(){
        return "Rectangle{width=" + width + " height=" + height + "}";
    }
}