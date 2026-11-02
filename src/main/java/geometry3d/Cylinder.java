package geometry3d;

import exceptions.InvalidFigureParametrException;
import geometry2d.Figure;

public class Cylinder {
    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) throws InvalidFigureParametrException{
        if(base == null){
            throw new InvalidFigureParametrException("Основание цилиндра не может быть null.");
        }
        if(height <= 0 || Double.isNaN(height) || Double.isInfinite(height)){
            throw new InvalidFigureParametrException("Высота должна быть положительным конечным числом");
        }

        this.base = base;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public Figure getBase() {
        return base;
    }

    public double volume(){
        return base.area() * height;
    }

    @Override
    public String toString(){
        return "Cylinder{base=" + base.toString() + ", height=" + height + "}";
    }
}