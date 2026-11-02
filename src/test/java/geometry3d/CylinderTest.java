package geometry3d;

import exceptions.InvalidFigureParametrException;
import geometry2d.Circle;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    private static final double EPSILON = 1e-9;

    @Test
    void testVolumeWithCircleBase() throws InvalidFigureParametrException {
        Circle base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 5.0);

        double expectedVolume = Math.PI * 2.0 * 2.0 * 5.0;
        assertEquals(expectedVolume, cylinder.volume(), EPSILON);
    }

    @Test
    void testVolumeWithRectangleBase() throws InvalidFigureParametrException {
        Rectangle base = new Rectangle(3.0, 4.0);
        Cylinder cylinder = new Cylinder(base, 10.0);
        assertEquals(120.0, cylinder.volume(), EPSILON);
    }

    @Test
    void testGetters() throws InvalidFigureParametrException {
        Circle base = new Circle(1.0);
        Cylinder cylinder = new Cylinder(base, 3.0);

        assertSame(base, cylinder.getBase(), "Геттер должен вернуть тот же объект основания");
        assertEquals(3.0, cylinder.getHeight(), EPSILON);
    }

    @Test
    void testToString() throws InvalidFigureParametrException {
        Circle base = new Circle(1.0);
        Cylinder cylinder = new Cylinder(base, 2.0);
        String result = cylinder.toString();

        assertTrue(result.contains("Cylinder"));
        assertTrue(result.contains("Circle"));
        assertTrue(result.contains("2.0"));
    }

    @Test
    void testNullBaseThrowsException() {
        assertThrows(InvalidFigureParametrException.class, () -> new Cylinder(null, 5.0));
    }

    @Test
    void testInvalidHeightThrowsException() throws InvalidFigureParametrException {
        Circle base = new Circle(1.0);

        assertThrows(InvalidFigureParametrException.class, () -> new Cylinder(base, -5.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Cylinder(base, 0.0));
        assertThrows(InvalidFigureParametrException.class, () -> new Cylinder(base, Double.NaN));
        assertThrows(InvalidFigureParametrException.class, () -> new Cylinder(base, Double.POSITIVE_INFINITY));
    }
}