package oracle.simulator.enums;

public enum Direction {
    N, E, S, W;

    public Direction left() {
        switch (this) {
            case N: return W;
            case E: return N;
            case S: return E;
            case W: return S;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public Direction right() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}