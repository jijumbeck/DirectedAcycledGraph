package jijumbeck.directed_acyclic_graph.points;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    void PointGetPositionShouldReturnCorrectCoord2D() {
        int x = 0, y = 1;
        Point point = new Point(x, y);
        Coord2D coord2D = new Coord2D(x, y);
        assertEquals(point.getPosition(), coord2D);
    }

    @Test
    void PointSetPositionShouldSetCorrectPosition() {
        int x = 0, y = 1;
        Point point = new Point(x, y);

        Coord2D coord2D = new Coord2D(-1, -1);
        point.setPosition(coord2D);

        assertEquals(point.getPosition(), coord2D);
    }

    @Test
    void PointGetBounBoxShouldReturnCorrectBoundBoxWithCorrectMinimalCoord2DWhichEqualsPoint() {
        int x = 0, y = 1;
        Point point = new Point(x, y);
        BoundBox boundBox = point.getBoundBox();

        assertEquals(boundBox.minimalPoint, point.position);
    }

    @Test
    void PointGetBoundBoxShouldReturnCorrectBoundBoxWithCorrectMaximumCoord2DWhichEqualsPoint() {
        int x = 0, y = 1;
        Point point = new Point(x, y);
        BoundBox boundBox = point.getBoundBox();

        assertEquals(boundBox.maximumPoint, point.position);
    }

    /*
    Tests if coordinates of both points of bound box are equal to point.
     */
    @Test
    void PointGetOffsetBoundBoxShouldReturnOffsetBoundBoxWithMinimalPointEqualToOriginalBoundBoxMinimalPoint() {
        int x = 0, y = 1;
        Point point = new Point(x, y);
        BoundBox boundBox = point.getBoundBox();
        BoundBox offsetBoundBox = point.getOffsetBoundBox();

        assertEquals(boundBox.minimalPoint, offsetBoundBox.minimalPoint);
    }

    /*
    Tests if coordinates of both points of bound box are equal to point.
     */
    @Test
    void PointGetOffsetBoundBoxShouldReturnOffsetBoundBoxWithMaximumPointEqualToOriginalBoundBoxMaximumPoint() {
        int x = 0, y = 1;
        Point point = new Point(x, y);
        BoundBox boundBox = point.getBoundBox();
        BoundBox offsetBoundBox = point.getOffsetBoundBox();

        assertEquals(boundBox.maximumPoint, offsetBoundBox.maximumPoint);
    }

    /*
    offset bound box is original bound box. it tests here.
     */
    @Test
    void PointGetOffsetBoundBoxEqualsOriginalBoundBox() {
        Point point = new Point(10, 0);
        assertAll(() -> {
            assertEquals(point.getBoundBox().maximumPoint, point.getOffsetBoundBox().maximumPoint);
            assertEquals(point.getBoundBox().minimalPoint, point.getOffsetBoundBox().minimalPoint);
        });
    }
}
