package oracle.simulator.enums;

public enum CostItem {
    COMMUNICATION(1, "communication overhead"),
    FUEL(1, "fuel usage"),
    UNCLEARED_SQUARE(3, "uncleared squares"),
    PROTECTED_TREE(10, "destruction of protected tree"),
    TREE_SCRATCH(2, "repairing paint damage to bulldozer clearable tree")
    ;

    private final int creditPerItem;
    private final String description;

    CostItem(int creditPerItem, String description) {
        this.creditPerItem = creditPerItem;
        this.description = description;
    }

    /**
     * Credit cost for each accounting item
     * @return credit
     */
    public int getCreditPerItem() {
        return creditPerItem;
    }

    /**
     * Description of the accounting item
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
