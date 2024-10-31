package oracle.simulator.enums;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SquareStateTest {

    @Test
    public void testNormalizeValidSquare() {
        Optional<SquareState> squareState = SquareState.normalize('o');
        assertEquals(SquareState.PLAIN, squareState.orElse(null));
    }

    @Test
    public void testNormalizeInvalidSquare() {
        Optional<SquareState> squareState = SquareState.normalize('x');
        assertFalse(squareState.isPresent());
    }

    @Test
    public void testGetFuel() {
        assertEquals(1, SquareState.PLAIN.getFuel());
        assertEquals(2, SquareState.REMOVABLE_TREE.getFuel());
    }
}