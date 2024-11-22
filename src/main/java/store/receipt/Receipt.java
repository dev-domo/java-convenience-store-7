package store.receipt;

import java.util.Map;
import java.util.Map.Entry;
import store.product.Product;

public class Receipt {


    private final Map<Product, Integer> orders;
    private final int total;
    private final int promotionDiscount;
    private final int membershipDiscount;

    public Receipt(final Map<Product, Integer> orders, final int total, final int promotionDiscount,
                   final int membershipDiscount) {
        this.orders = orders;
        this.total = total;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
    }

    public String purchasedProduct() {
        StringBuilder builder = new StringBuilder();
        for (Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            int purchaseCount = entry.getValue();
            builder.append(product.getName()).append("\t\t").append(purchaseCount).append("\t")
                    .append(product.calculateTotal(purchaseCount));
        }
        return builder.toString();
    }

    public String giveawayProduct() {
        StringBuilder builder = new StringBuilder();
        for (Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            int purchaseCount = entry.getValue();
            if (product.isPromotional()) {
                builder.append(product).append("\t\t").append(product.calculateGiveaway(purchaseCount));
            }
        }
        return builder.toString();
    }

    public String getTotal() {
        return Integer.toString(total);
    }

    public String getPromotionDiscount() {
        return Integer.toString(promotionDiscount);
    }

    public String getMembershipDiscount() {
        return Integer.toString(membershipDiscount);
    }

    public String getFinalPayment() {
        return Integer.toString(total - promotionDiscount - membershipDiscount);
    }
}
