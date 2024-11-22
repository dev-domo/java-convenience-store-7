package store.order;

import java.util.Map.Entry;
import store.membership.Membership;
import store.product.Product;
import store.product.Products;
import store.product.PromotionProduct;
import store.receipt.Receipt;

public class Payment {

    private final OrderProducts orderProducts;
    private final Membership membership;
    private final Products products;

    public Payment(final OrderProducts orderProducts, final Products products) {
        this.orderProducts = orderProducts;
        this.membership = new Membership();
        this.products = products;
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
        if (product.isPromotional()) {
            PromotionProduct promotionProduct = (PromotionProduct) product;
            promotionProduct.deduct(purchaseCount);
            return;
        }
        product.deduct(purchaseCount);
    }
}
