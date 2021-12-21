package jijumbeck.directed_acyclic_graph.points;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Coord2DTest {
    @Test
    void coord2DEqualsShouldReturnTrueOnSameReference() {
        Coord2D coord2D = new Coord2D(-1, 0);
        assertEquals(coord2D, coord2D);
    }

    @Test
    void coord2DEqualsShouldReturnFalseOnNullReference() {
        Coord2D coord2D = new Coord2D(-1, 0);
        assertNotEquals(null, coord2D);
    }

    @Test
    void coord2DEqualsShouldReturnFalseOnNotCoord2DInstance() {
        Coord2D coord2D = new Coord2D(-1, 0);
        String stringForTestEquals = "testForTestEquals";
        assertNotEquals(coord2D, stringForTestEquals);
    }

    @Test
    void coord2DEqualsShouldReturnTrueOnCoord2DsWithEqualCoordinates() {
        Coord2D coord2D1 = new Coord2D(-1, 0);
        Coord2D coord2D2 = new Coord2D(-1, 0);
        assertEquals(coord2D1, coord2D2);
    }

    @Test
    void coord2DEqualsShouldReturnFalseOnNotCoord2DsWithNonEqualCoordinates() {
        Coord2D coord2D1 = new Coord2D(-1, 0);
        Coord2D coord2D2 = new Coord2D(-1, 1);
        assertNotEquals(coord2D1, coord2D2);
    }

    @Test
    void coord2DgetXShouldReturnX() {
        int x = 0, y = 1;
        Coord2D coord2D1 = new Coord2D(x, y);
        assertEquals(coord2D1.getX(), x);
    }

    @Test
    void coord2DgetYShouldReturnY() {
        int x = 0, y = 1;
        Coord2D coord2D1 = new Coord2D(x, y);
        assertEquals(coord2D1.getY(), y);
    }
}
