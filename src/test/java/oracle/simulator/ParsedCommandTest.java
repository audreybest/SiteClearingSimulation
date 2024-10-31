package oracle.simulator;

import oracle.simulator.enums.Command;
import oracle.simulator.exceptions.InvalidCommandException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ParsedCommandTest {

    @Test
    public void testParseValidCommand() throws InvalidCommandException {
        ParsedCommand command = ParsedCommand.parse("a 4");
        assertEquals(Command.ADVANCE, command.getCommand());
        assertEquals(4, command.getStep());
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> {
            ParsedCommand.parse("invalid");
        });
    }

    @Test
    public void testParseQuitCommand() throws InvalidCommandException {
        ParsedCommand command = ParsedCommand.parse("q");
        assertEquals(Command.QUIT, command.getCommand());
    }
}