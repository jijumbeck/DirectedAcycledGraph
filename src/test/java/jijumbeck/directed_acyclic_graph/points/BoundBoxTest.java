package jijumbeck.directed_acyclic_graph.points;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoundBoxTest {
    /*
    Tests if coordinates are valid.
     */
    @Test
    void BoundBoxCtorShouldThrowIllegalArgumentExceptionOnInvalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> new BoundBox(new Coord2D(20, 10), new Coord2D(-1, 0)));
    }

    /*
    Tests merging of default bound box and non default.
     */
    @Test
    void mergeBoundBoxShouldMergeDefaultBoundBoxWithNoDefaultBoundBox () {
        BoundBox defaultBoundBox = BoundBox.defaultBoundBox();
        BoundBox nonDefaultBoundBox = new BoundBox(new Coord2D(0, 0), new Coord2D(1, 1));

        defaultBoundBox.merge(nonDefaultBoundBox);
        assertAll(() -> {
            assertEquals(defaultBoundBox.minimalPoint, nonDefaultBoundBox.minimalPoint);
            assertEquals(defaultBoundBox.maximumPoint, nonDefaultBoundBox.maximumPoint);
        });
    }
}
