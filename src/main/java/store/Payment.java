package store;

import java.util.Map.Entry;
import store.product.Product;

public class Payment {

    private final OrderProducts orderProducts;
    private final Membership membership;

    public Payment(final OrderProducts orderProducts) {
        this.orderProducts = orderProducts;
        this.membership = new Membership();
    }

    public Receipt progress(boolean hasMembership) {
        int total = 0;
        int promotionDiscount = 0;
        int membershipDiscount = 0;

        for (Entry<Product, Integer> order : orderProducts.getOrders().entrySet()) {
            Product product = order.getKey();
            int purchaseCount = order.getValue();
            reduceInventory(product, purchaseCount);

            total += product.calculateTotal(purchaseCount);
            promotionDiscount += product.calculatePromotionDiscount(purchaseCount);
            if (hasMembership && !product.isPromotional()) {
                membershipDiscount += membership.calculateDiscount(total);
            }
        }

        return new Receipt(orderProducts.getOrders(), total, promotionDiscount, membershipDiscount);
    }

    private void reduceInventory(Product product, int purchaseCount) {
        product.deduct(purchaseCount);
    }
}
