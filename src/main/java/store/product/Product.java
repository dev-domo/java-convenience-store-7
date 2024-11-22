package store.product;

import java.text.DecimalFormat;

public class Product {

    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");
    private static final String WON = "원";
    private static final String NO_QUANTITY = "재고 없음";
    private static final String COUNT = "개";
    private static final String NULL_STATE = "null";

    private final String name;
    protected final int price;
    protected int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String state = "";
        state += DASH + SPACE + name + SPACE + PRICE_FORMAT.format(price) + WON + SPACE + quantityState();
        return state;
    }

    private String quantityState() {
        if (quantity == 0) {
            return NO_QUANTITY;
        }
        return quantity + COUNT;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public boolean isPromotional() {
        return this instanceof PromotionProduct;
    }

    public boolean isLackQuantity(int purchaseCount) {
        return quantity < purchaseCount;
    }

    public int insufficientNumberOfProducts(int purchaseCount) {
        return purchaseCount - quantity;
    }

    public String getName() {
        return name;
    }

    public int calculateTotal(int purchaseCount) {
        return price * purchaseCount;
    }

    public void deduct(int purchaseCount) {
        quantity -= purchaseCount;
    }

    public int calculatePromotionDiscount(int purchaseCount) {
        if (isPromotional()) {
            PromotionProduct promotionProduct = (PromotionProduct) this;
            return promotionProduct.calculatePromotionDiscount(purchaseCount);
        }
        return 0;
    }

    public String calculateGiveaway(int purchaseCount) {
        PromotionProduct promotionProduct = (PromotionProduct) this;
        return promotionProduct.calculateGiveaway(purchaseCount);
    }
}
