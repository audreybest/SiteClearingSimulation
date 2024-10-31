package oracle.simulator.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String command) {
        super("The input command is invalid: " + command);
    }
}
