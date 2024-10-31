package oracle.simulator;

import oracle.simulator.enums.Command;
import oracle.simulator.exceptions.InvalidCommandException;

public class ParsedCommand {
    private final String rawCommand;
    private final Command command;
    private final int step;

    public ParsedCommand(String rawCommand, Command command, int step) {
        this.rawCommand = rawCommand;
        this.command = command;
        this.step = step;
    }

    public String getRawCommand() {
        return rawCommand;
    }

    public Command getCommand() {
        return command;
    }

    public int getStep() {
        return step;
    }

    /**
     * A utility function to parse the input command.
     * @param rawCommand the command text
     * @return parsed command
     * @throws InvalidCommandException if the command is invalid
     */
    public static ParsedCommand parse(String rawCommand) throws InvalidCommandException {
        if (rawCommand == null || rawCommand.isEmpty()) {
            throw new InvalidCommandException("Command cannot be null or empty");
        }

        String[] parts = rawCommand.split(" ");
        Command command;
        int step = 0;

        switch (parts[0]) {
            case "l":
                command = Command.LEFT;
                break;
            case "r":
                command = Command.RIGHT;
                break;
            case "a":
                if (parts.length != 2) {
                    throw new InvalidCommandException("Advance command must be followed by a number");
                }
                try {
                    step = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Invalid number format for advance command");
                }
                command = Command.ADVANCE;
                break;
            case "q":
                command = Command.QUIT;
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + parts[0]);
        }

        return new ParsedCommand(rawCommand, command, step);
    }

    @Override
    public String toString() {
        switch (command) {
            case ADVANCE:
                return "advance " + step;
            case LEFT:
                return "turn left";
            case RIGHT:
                return "turn right";
            case QUIT:
                return "quit";
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }
}
