package geometry2d;

import exceptions.InvalidFigureParametrException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void testAreaAndPerimeter() throws InvalidFigureParametrException {
        Circle circle = new Circle(5.0);

        double expectedArea = Math.PI * 25.0;
        assertEquals(expectedArea, circle.area(), EPSILON);

        double expectedPerimeter = 2 * Math.PI * 5.0;
        assertEquals(expectedPerimeter, circle.perimetr(), EPSILON);
    }

    @Test
    void testGetters() throws InvalidFigureParametrException {
        Circle circle = new Circle(3.5);
        assertEquals(3.5, circle.getRadius(), EPSILON);
    }

    @Test
    void testToString() throws InvalidFigureParametrException {
        Circle circle = new Circle(2.0);
        String result = circle.toString();
        assertTrue(result.contains("Circle"), "Строка должна содержать тип фигуры");
        assertTrue(result.contains("2.0"), "Строка должна содержать радиус");
    }

    @Test
    void testNegativeRadiusThrowsException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Circle(-1.0));
    }

    @Test
    void testZeroRadiusThrowsException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Circle(0.0));
    }

    @Test
    void testNaNRadiusThrowsException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Circle(Double.NaN));
    }

    @Test
    void testInfiniteRadiusThrowsException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }
}