package jijumbeck.directed_acyclic_graph.points;

import jijumbeck.directed_acyclic_graph.exceptions.DAGConstraintException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OriginTest {
    @Test
    void OriginCtorShouldNotThrowDAGConstraintExceptionOnSettingCorrectSet() {
        Origin origin1 = new Origin(0, 1);
        Origin origin2 = new Origin(1, 0);

        Set<Point> set = new HashSet<Point>();
        set.add(origin2);

        assertDoesNotThrow(() -> origin1.setChildren(set));
    }

    @Test
    void OriginCtorShouldThrowDAGConstraintExceptionOnCycle() {
        Origin origin1 = new Origin(0, 1);
        Origin origin2 = new Origin(1, 0);

        Set<Point> set1 = new HashSet<Point>();
        set1.add(origin2);

        assertThrows(DAGConstraintException.class, () -> {
            origin1.setChildren(set1);
            Set<Point> set2 = new HashSet<Point>();
            set2.add(origin1);
            origin2.setChildren(set2);
        });
    }

    @Test
    void OriginCtorShouldThrowDAGConstraintExceptionOnSettingSetWithSameOrigin() {
        Origin origin1 = new Origin(0, 1);

        Set<Point> set = new HashSet<Point>();
        set.add(origin1);

        assertThrows(DAGConstraintException.class, () -> origin1.setChildren(set));
    }

    @Test
    void OriginGetBoundBoxShouldReturnCorrectBoundBoxOfTwoPoints() {
        Origin origin1 = new Origin(0, 1);
        Point minimalPoint = new Point(-1, -1);
        Point maximumPoint = new Point(1, 1);

        Set<Point> set = new HashSet<Point>();
        set.add(minimalPoint);
        set.add(maximumPoint);

        assertAll(() -> {
            origin1.setChildren(set);
            BoundBox boundBox = origin1.getBoundBox();
            assertEquals(boundBox.minimalPoint, minimalPoint.position);
            assertEquals(boundBox.maximumPoint, maximumPoint.position);
        });
    }

    @Test
    void OriginGetBoundBoxShouldReturnCorrectBoundBoxOfMoreThanTwoPoints() {
        Origin origin1 = new Origin(0, 1);
        Point firstPoint = new Point(-1, -1);
        Point secondPoint = new Point(1, 1);
        Point thirdPoint = new Point(0, 0);

        Set<Point> set = new HashSet<Point>();
        set.add(firstPoint);
        set.add(secondPoint);
        set.add(thirdPoint);

        assertAll(() -> {
            origin1.setChildren(set);
            BoundBox boundBox = origin1.getBoundBox();
            assertEquals(boundBox.minimalPoint, new Coord2D(-1, -1));
            assertEquals(boundBox.maximumPoint, new Coord2D(1,1));
        });
    }

    @Test
    void OriginGetBoundBoxShouldReturnCorrectBoundBoxOfPointsAndDefault() {
        Origin origin1 = new Origin(0, 1);
        Point minimalPoint = new Point(-1, -1);
        Point maximumPoint = new Point(1, 1);
        Origin origin2 = new Origin(0, -1);

        Set<Point> set = new HashSet<Point>();
        set.add(minimalPoint);
        set.add(maximumPoint);
        set.add(origin2);

        assertAll(() -> {
            origin1.setChildren(set);
            BoundBox boundBox = origin1.getBoundBox();
            assertEquals(boundBox.minimalPoint, minimalPoint.position);
            assertEquals(boundBox.maximumPoint, maximumPoint.position);
        });
    }

    @Test
    void OriginGetBoundBoxShouldReturnDefaultBoundBox() {
        Origin origin1 = new Origin(0, 1);
        Origin origin2 = new Origin(0, -1);

        Set<Point> set = new HashSet<Point>();
        set.add(origin2);

        assertAll(() -> {
            origin1.setChildren(set);
            BoundBox boundBox = origin1.getBoundBox();
            assertTrue(boundBox.isDefaultForEmptyOrigin);
        });
    }

    @Test
    void OriginGetChildrenShouldReturnCopyOfSetChildren() {
        Origin origin1 = new Origin(0, 1);
        Origin origin2 = new Origin(0, -1);
        Origin origin3 = new Origin(1,1);

        Set<Point> set = new HashSet<Point>();
        set.add(origin2);

        assertAll(() -> {
            origin1.setChildren(set);
            Set<Point> getChildren = origin1.getChildren();
            getChildren.add(origin3);
            assertNotEquals(origin1.getChildren(), getChildren);
        });
    }

    @Test
    void OriginTraverseChildrenShouldWorkSuccessfullyOnValidData() {
        assertDoesNotThrow(() -> {
            Point point1 = new Point(1, 1);
            Origin origin1 = new Origin(1, 2);
            Set<Point> set1 = new HashSet<>();
            set1.add(point1);
            origin1.setChildren(set1);

            Point point2 = new Point(0, 0);
            Origin origin2 = new Origin(3, 5);
            Set<Point> set2 = new HashSet<>();
            set2.add(point2);
            set2.add(point1);
            set2.add(origin1);

            origin2.setChildren(set2);
        });
    }

    @Test
    void OriginTraverseChildrenShouldWorkSuccessfullyOnInvalidData() {
        assertThrows(DAGConstraintException.class, () -> {
            Origin origin3 = new Origin(1, 1);

            Origin origin1 = new Origin(1, 2);
            Set<Point> set1 = new HashSet<>();
            set1.add(origin3);
            origin1.setChildren(set1);

            Point point2 = new Point(0, 0);
            Origin origin2 = new Origin(3, 5);
            Set<Point> set2 = new HashSet<>();
            set2.add(point2);
            set2.add(origin3);
            set2.add(origin1);

            Set<Point> set3 = new HashSet<>();
            set3.add(origin2);
            origin3.setChildren(set3);

            origin2.setChildren(set2);
        });
    }
}
