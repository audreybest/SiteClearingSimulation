package oracle.simulator;


public interface SiteCleanupSimulator {

    /**
     * A method to check the current state and return a verdict for whether
     * to quit the simulation. The simulation should end in the following cases:
     *  1. a 'quit' command issued
     *  2. the bulldozer encountered a preserved tree
     *  3. the bulldozer went out of the boundary
     * @return true if the simulation should be ended
     */
    boolean shouldQuitSimulation();

    /**
     * A method to execute a command.
     * @param cmd the command
     */
    void executeCommand(ParsedCommand cmd);

    /**
     * Print out simulation summary
     */
    void printSimulationSummary();

    CostCalculator getCostCalculator();
}
