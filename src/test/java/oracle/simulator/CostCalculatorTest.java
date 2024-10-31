package oracle.simulator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostCalculatorTest {

    private CostCalculator costCalculator;
    private char[][] siteMapArray;

    @Before
    public void setUp() {
        siteMapArray = new char[][] {
                {'c', 'c', 'c', 'c', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'c', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'c', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'c', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'c', 'c', 'c', 'c', 'c', 'c', 'c'}
        };

    }

    @Test
    public void testAddCommunicationOverhead() {
        costCalculator = new CostCalculator(siteMapArray);
        costCalculator.addCommunicationOverhead();
        assertEquals(1, costCalculator.getCommunicationOverhead());
    }

    @Test
    public void testAddFuelUsage() {
        costCalculator = new CostCalculator(siteMapArray);
        costCalculator.addFuelUsage(5);
        assertEquals(5, costCalculator.getFuelUsage());
    }

    @Test
    public void testAddProtectedTreeDestruction() {
        costCalculator = new CostCalculator(siteMapArray);
        costCalculator.addProtectedTreeDestruction();
        assertEquals(10, costCalculator.getProtectedTreeDestruction());
    }

    @Test
    public void testAddPaintDamage() {
        costCalculator = new CostCalculator(siteMapArray);
        costCalculator.addPaintDamage();
        assertEquals(2, costCalculator.getPaintDamage());
    }

    @Test
    public void testCalculateUnclearedSquaresCost() {
        costCalculator = new CostCalculator(siteMapArray);
        int cost = costCalculator.calculateUnclearedSquaresCost();
        assertEquals(102, cost);
    }

    @Test
    public void testGetTotalCost() {
        costCalculator = new CostCalculator(siteMapArray);
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addCommunicationOverhead();
        costCalculator.addFuelUsage(19);
        costCalculator.addPaintDamage();
        int totalCost = costCalculator.getTotalCost();
        assertEquals(130, totalCost);
    }
}