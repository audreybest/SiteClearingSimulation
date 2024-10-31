package oracle.simulator;

import oracle.simulator.enums.Command;
import oracle.simulator.enums.Direction;
import oracle.simulator.enums.SquareState;
import oracle.simulator.exceptions.InvalidSiteMapException;

import java.util.LinkedList;

public class SiteCleanupSimulatorImpl implements SiteCleanupSimulator {

    private char[][] siteMapArray;
    private LinkedList<ParsedCommand> commands;
    private int bullDozerIndI;
    private int bullDozerIndJ;
    private Direction bullDozerFacingDirection;
    private CostCalculator costCalculator;

    public SiteCleanupSimulatorImpl(char[][] siteMapArray) throws InvalidSiteMapException {
        this.siteMapArray = siteMapArray;
        this.commands = new LinkedList<>();
        this.costCalculator = new CostCalculator(siteMapArray);
        siteMapValidation();
        bullDozerIndI = 0;
        bullDozerIndJ = -1;
        bullDozerFacingDirection = Direction.E;
    }

    private void siteMapValidation() throws InvalidSiteMapException {
        // nullability check
        if (this.siteMapArray == null || this.siteMapArray.length == 0) {
            throw new InvalidSiteMapException("Site map is empty");
        }

        int rows = this.siteMapArray.length;
        int cols = this.siteMapArray[0].length;

        // Check rectangle shape
        for (char[] row : this.siteMapArray) {
            if (row.length != cols) {
                throw new InvalidSiteMapException("Site map is not rectangle");
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = this.siteMapArray[i][j];
                SquareState.normalize(c)
                        .orElseThrow(() -> new InvalidSiteMapException("Invalid char found: " + c));
            }
        }
    }

    @Override
    public boolean shouldQuitSimulation() {
        if (this.commands.isEmpty()) {
            return false;
        }
        return this.commands.getLast().getCommand() == Command.QUIT
                || isOutOfBound()
                || isProtectedTree();
    }

    @Override
    public void executeCommand(ParsedCommand parsedCommand) {
        if (parsedCommand.getCommand() != Command.QUIT) {
            costCalculator.addCommunicationOverhead();
        }
        this.commands.add(parsedCommand);

        if (parsedCommand.getCommand() == Command.LEFT) {
            this.bullDozerFacingDirection = this.bullDozerFacingDirection.left();
        } else if (parsedCommand.getCommand() == Command.RIGHT) {
            this.bullDozerFacingDirection = this.bullDozerFacingDirection.right();
        } else if (parsedCommand.getCommand() == Command.ADVANCE) {
            for (int steps = parsedCommand.getStep(); steps > 0; steps--) {
                if (this.bullDozerFacingDirection == Direction.E) {
                    this.bullDozerIndJ++;
                } else if (this.bullDozerFacingDirection == Direction.N) {
                    this.bullDozerIndI--;
                } else if (this.bullDozerFacingDirection == Direction.S) {
                    this.bullDozerIndI++;
                } else { // W
                    this.bullDozerIndJ--;
                }
                if (isOutOfBound()) {
                    break;
                }
                if (isProtectedTree()) {
                    costCalculator.addProtectedTreeDestruction();
                    break;
                }
                calculateFuelUsage(steps > 1);
                if (isRockySquare() || isRemovableTree()) {
                    this.siteMapArray[bullDozerIndI][bullDozerIndJ] = SquareState.CLEARED.getCode();
                }
            }
        }
    }

    private void calculateFuelUsage(boolean isPassingThrough) {
        char currentSquare = siteMapArray[bullDozerIndI][bullDozerIndJ];
        SquareState squareState = SquareState.normalize(currentSquare)
                .orElseThrow(() -> new IllegalArgumentException("Invalid square state: " + currentSquare));
        costCalculator.addFuelUsage(squareState.getFuel());
        if (isPassingThrough && squareState == SquareState.REMOVABLE_TREE) {
            costCalculator.addPaintDamage();
        }
        siteMapArray[bullDozerIndI][bullDozerIndJ] = SquareState.CLEARED.getCode();
    }

    private boolean isRemovableTree() {
        return siteMapArray[bullDozerIndI][bullDozerIndJ] == SquareState.REMOVABLE_TREE.getCode();
    }

    private boolean isRockySquare() {
        return siteMapArray[bullDozerIndI][bullDozerIndJ] == SquareState.ROCKY.getCode();
    }

    private boolean isProtectedTree() {
        return siteMapArray[bullDozerIndI][bullDozerIndJ] == SquareState.PROTECTED_TREE.getCode();
    }

    private boolean isOutOfBound() {
        return bullDozerIndI < 0
                || bullDozerIndI >= siteMapArray.length
                || bullDozerIndJ < 0
                || bullDozerIndJ >= siteMapArray[0].length;
    }

    @Override
    public void printSimulationSummary() {
        System.out.println("The simulation has ended at your request. These are the commands you issued:");
        for (ParsedCommand command : commands) {
            System.out.print(command + ", ");
        }
        System.out.println();

        costCalculator.printSummary();
    }

    @Override
    public CostCalculator getCostCalculator() {
        return costCalculator;
    }
}