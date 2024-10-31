package oracle.simulator.enums;

/**
 * An enum for all valid site cleanup command
 */
public enum Command {
    ADVANCE("a", "advance", "advance"),
    LEFT("l", "left", "turn left"),
    RIGHT("r", "right", "turn right"),
    QUIT("q", "quit", "quit"),
    ;
    private final String shortInput;
    private final String longInput;
    private final String description;

    Command(String shortInput, String longInput, String description) {
        this.shortInput = shortInput;
        this.longInput = longInput;
        this.description = description;
    }

    public String getShortInput() {
        return shortInput;
    }

    public String getLongInput() {
        return longInput;
    }

    public String getDescription() {
        return description;
    }
}
