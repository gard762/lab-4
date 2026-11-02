package geometry2d;

import exceptions.InvalidFigureParametrException;

import java.io.Serializable;

public class Circle implements Figure, Serializable {
    private static final long serialVersionUID = 1L;
    private final double radius;

    public Circle(double radius) throws InvalidFigureParametrException {
        if(radius <= 0 || Double.isInfinite(radius) || Double.isNaN(radius)){
            throw new InvalidFigureParametrException("Радиус должен быть положительным конечным числом");
        }
        this.radius = radius;
    }

    @Override
    public double area(){
        return Math.PI * radius * radius;
    }

    @Override
    public double perimetr(){
        return 2 * Math.PI * radius;
    }

    public double getRadius(){
        return radius;
    }

    @Override
    public String toString(){
        return "Circle{radius=" + radius + "}";
    }
}