package store.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private Promotion promotion;

    public Product(String name, double price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        String formattedPrice = currencyFormat.format(price) + "원";

        String stockStatus = quantity > 0 ? quantity + "개" : "재고 없음";

        String promotionName = promotion != null ? promotion.getName() : "";

        return String.format("- %s %s %s %s", name, formattedPrice, stockStatus, promotionName).trim();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void decreaseQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
    }

    public int calculateNonPromoQuantity(int orderQuantity) {
        if (promotion == null) return 0;
        return orderQuantity % promotion.getCondition();
    }
}