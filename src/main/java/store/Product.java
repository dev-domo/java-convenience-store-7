package store;

import java.text.DecimalFormat;

public class Product {

    private static final String NO_WORD = "";
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");
    private static final String WON = "원";
    private static final String NO_QUANTITY = "재고 없음";
    private static final String COUNT = "개";

    private final String name;
    private final int price;
    private final int quantity;
    private final Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        String state = NO_WORD;
        state += DASH + SPACE + name + SPACE + PRICE_FORMAT.format(price) + WON + SPACE + quantityState()
                + promotionState();
        return state;
    }

    private String quantityState() {
        if (quantity == 0) {
            return NO_QUANTITY;
        }
        return quantity + COUNT;
    }

    private String promotionState() {
        if (promotion == null) {
            return NO_WORD;
        }
        return promotion.toNameString();
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
