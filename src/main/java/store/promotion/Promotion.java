package store.promotion;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int buy;
    private final int get;

    public Promotion(final String name, final int buy, final int get, final LocalDate startDate,
                     final LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public String toNameString() {
        return " " + name;
    }

    public int calculateNotAdaptedPurchaseCount(int purchaseCount) {
        return purchaseCount % (buy + get);
    }

    public boolean lessThanBuy(int purchaseCount) {
        return purchaseCount < buy;
    }

    public int calculateMoreFreeProductCount(int purchaseCount) {
        return buy - purchaseCount;
    }

    public int calculateDiscount(int purchaseCount) {
        return purchaseCount / (buy + get);
    }
}
