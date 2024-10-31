package oracle.simulator.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void testLeftTurn() {
        assertEquals(Direction.N, Direction.E.left());
        assertEquals(Direction.W, Direction.N.left());
    }

    @Test
    public void testRightTurn() {
        assertEquals(Direction.S, Direction.E.right());
        assertEquals(Direction.E, Direction.N.right());
    }
}