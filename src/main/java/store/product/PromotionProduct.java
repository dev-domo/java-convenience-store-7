package store.product;

import store.promotion.Promotion;

public class PromotionProduct extends Product {

    private static final String NO_WORD = "";

    private final Promotion promotion;

    public PromotionProduct(String name, int price, int quantity, Promotion promotion) {
        super(name, price, quantity);
        this.promotion = promotion;
    }

    public int confirmLackQuantity(int purchaseCount) {
        return promotion.calculateNotAdaptedPurchaseCount(purchaseCount);
    }

    public boolean possibleGetMore(int purchaseCount) {
        return promotion.lessThanBuy(purchaseCount);
    }

    public int confirmCanGetFreeProductCount(int purchaseCount) {
        return promotion.calculateMoreFreeProductCount(purchaseCount);
    }

    public int calculatePromotionDiscount(int purchaseCount) {
        if (promotion != null) {
            return super.price * promotion.calculateDiscount(purchaseCount);
        }
        return 0;
    }

    public void deduct(int purchaseCount) {
        if (promotion == null) {
            super.deduct(purchaseCount);
        }
        if (quantity < purchaseCount) {
            quantity = 0;
            super.deduct(purchaseCount - quantity);
        }
        quantity -= purchaseCount;
    }

    @Override
    public String calculateGiveaway(int purchaseCount) {
        return Integer.toString(promotion.calculateDiscount(purchaseCount));
    }

    @Override
    public String toString() {
        return super.toString() + promotionState();
    }

    private String promotionState() {
        if (promotion == null) {
            return NO_WORD;
        }
        return promotion.toNameString();
    }
}
