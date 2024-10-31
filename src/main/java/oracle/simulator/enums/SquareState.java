package oracle.simulator.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SquareState {
    PLAIN('o', 1),
    ROCKY('r', 2),
    REMOVABLE_TREE('t', 2),
    PROTECTED_TREE('T', 0),
    CLEARED('c', 0)
    ;

    private final char code;
    private final int fuel;

    SquareState(char code, int fuel) {
        this.code = code;
        this.fuel = fuel;
    }

    public char getCode() {
        return code;
    }

    public int getFuel() {
        return fuel;
    }

    public static Optional<SquareState> normalize(char c) {
        return Arrays.stream(SquareState.values())
                .filter(e -> e.code == c)
                .findFirst();
    }
}
