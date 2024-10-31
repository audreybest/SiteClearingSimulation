package oracle.simulator;

import oracle.simulator.exceptions.InvalidCommandException;
import oracle.simulator.exceptions.InvalidSiteMapException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SiteCleanupSimulatorImplTest {

    private SiteCleanupSimulator simulator;
    private char[][] siteMapArray;

    @Before
    public void setUp() throws InvalidSiteMapException {
        siteMapArray = new char[][] {
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };
    }

    @Test
    public void testCommandSequence() throws InvalidCommandException, InvalidSiteMapException {
        simulator = new SiteCleanupSimulatorImpl(siteMapArray);
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("r"));
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("l"));
        simulator.executeCommand(ParsedCommand.parse("a 2"));
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("l"));
        simulator.executeCommand(ParsedCommand.parse("q"));

        assertEquals(true, simulator.shouldQuitSimulation());

        printSiteMap(siteMapArray);

        simulator.printSimulationSummary();
    }

    @Test
    public void testInvalidCommand() throws InvalidSiteMapException {
        simulator = new SiteCleanupSimulatorImpl(siteMapArray);
        assertThrows(InvalidCommandException.class, () -> {
            simulator.executeCommand(ParsedCommand.parse("invalid"));
        });
    }

    @Test
    public void testBoundaryCondition() throws InvalidCommandException, InvalidSiteMapException {
        simulator = new SiteCleanupSimulatorImpl(siteMapArray);
        simulator.executeCommand(ParsedCommand.parse("a 11"));
        assertEquals(true, simulator.shouldQuitSimulation());
    }

    @Test
    public void testProtectedTree() throws InvalidCommandException, InvalidSiteMapException {
        simulator = new SiteCleanupSimulatorImpl(siteMapArray);
        simulator.executeCommand(ParsedCommand.parse("a 8"));
        simulator.executeCommand(ParsedCommand.parse("r"));
        simulator.executeCommand(ParsedCommand.parse("a 7"));
        assertEquals(true, simulator.shouldQuitSimulation());
    }

    @Test
    public void testFuelUsage() throws InvalidCommandException, InvalidSiteMapException {
        simulator = new SiteCleanupSimulatorImpl(siteMapArray);
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("r"));
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("l"));
        simulator.executeCommand(ParsedCommand.parse("a 2"));
        simulator.executeCommand(ParsedCommand.parse("a 4"));
        simulator.executeCommand(ParsedCommand.parse("l"));
        simulator.executeCommand(ParsedCommand.parse("q"));

        int expectedFuelUsage = 19;
        assertEquals(expectedFuelUsage, simulator.getCostCalculator().getFuelUsage());
    }

    private void printSiteMap(char[][] siteMapArray) {
        for (char[] row : siteMapArray) {
            for (char square : row) {
                System.out.print(square + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}