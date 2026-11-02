package geometry2d;

import exceptions.InvalidFigureParametrException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void testAreaAndPerimeterNormal() throws InvalidFigureParametrException {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rect.area(), EPSILON);
        assertEquals(14.0, rect.perimetr(), EPSILON);
    }

    @Test
    void testAreaAndPerimeterSquare() throws InvalidFigureParametrException {
        Rectangle square = new Rectangle(5.0, 5.0);
        assertEquals(25.0, square.area(), EPSILON);
        assertEquals(20.0, square.perimetr(), EPSILON);
    }

    @Test
    void testGetters() throws InvalidFigureParametrException {
        Rectangle rect = new Rectangle(2.5, 7.0);
        assertEquals(2.5, rect.getWidth(), EPSILON);
        assertEquals(7.0, rect.getHeight(), EPSILON);
    }

    @Test
    void testToString() throws InvalidFigureParametrException {
        Rectangle rect = new Rectangle(1.0, 2.0);
        String result = rect.toString();
        assertTrue(result.contains("Rectangle"));
        assertTrue(result.contains("1.0"));
        assertTrue(result.contains("2.0"));
    }

    @Test
    void testInvalidParametersThrowException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(-1.0, 5.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(5.0, -1.0));

        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(0.0, 5.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(5.0, 0.0));

        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(Double.NaN, 5.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(5.0, Double.NaN));

        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(Double.POSITIVE_INFINITY, 5.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Rectangle(5.0, Double.NEGATIVE_INFINITY));
    }
}