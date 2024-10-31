package oracle.simulator;

import oracle.simulator.enums.SquareState;

public class CostCalculator{
    private int communicationOverhead;
    private int fuelUsage;
    private int protectedTreeDestruction;
    private int paintDamage;
    private char[][] siteMapArray;

    public CostCalculator(char[][] siteMapArray) {
        this.siteMapArray = siteMapArray;
        this.communicationOverhead = 0;
        this.fuelUsage = 0;
        this.protectedTreeDestruction = 0;
        this.paintDamage = 0;
    }

    public void addCommunicationOverhead() {
        communicationOverhead += 1;
    }

    public void addFuelUsage(int fuel) {
        fuelUsage += fuel;
    }

    public void addProtectedTreeDestruction() {
        protectedTreeDestruction += 10;
    }

    public void addPaintDamage() {
        paintDamage += 2;
    }

    public int calculateUnclearedSquaresCost() {
        int unclearedSquares = 0;
        for (char[] row : siteMapArray) {
            for (char square : row) {
                if (square != SquareState.PROTECTED_TREE.getCode() && square != SquareState.CLEARED.getCode()) {
                    unclearedSquares++;
                }
            }
        }
        return unclearedSquares * 3;
    }

    public int getTotalCost() {
        int unclearedSquaresCost = calculateUnclearedSquaresCost();
        return communicationOverhead + fuelUsage + protectedTreeDestruction + paintDamage + unclearedSquaresCost;
    }

    public int getCommunicationOverhead() {
        return communicationOverhead;
    }

    public int getFuelUsage() {
        return fuelUsage;
    }

    public int getProtectedTreeDestruction() {
        return protectedTreeDestruction;
    }

    public int getPaintDamage() {
        return paintDamage;
    }

    public void printSummary() {
        int unclearedSquaresCost = calculateUnclearedSquaresCost();
        int totalCost = getTotalCost();

        System.out.println("The costs for this land clearing operation were:");
        System.out.println();
        System.out.println("Item     Quantity      Cost");
        System.out.println("communication overhead " + communicationOverhead + " " + communicationOverhead);
        System.out.println("fuel usage " + fuelUsage + " " + fuelUsage);
        System.out.println("uncleared squares " + (unclearedSquaresCost / 3) + " " + unclearedSquaresCost);
        System.out.println("destruction of protected tree " + (protectedTreeDestruction / 10) + " " + protectedTreeDestruction);
        System.out.println("paint damage to bulldozer " + (paintDamage / 2) + " " + paintDamage);
        System.out.println("----");
        System.out.println("Total " + totalCost);
    }
}