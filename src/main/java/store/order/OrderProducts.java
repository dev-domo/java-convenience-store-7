package store.order;

import java.util.Map;
import java.util.Map.Entry;
import store.product.Product;
import store.product.PromotionProduct;
import view.InputView;

public class OrderProducts {

    private Map<Product, Integer> orders;
    private final InputView inputView;

    public OrderProducts(Map<Product, Integer> orders) {
        this(orders, new InputView());
    }

    public OrderProducts(Map<Product, Integer> orders, final InputView inputView) {
        this.orders = orders;
        this.inputView = inputView;
    }

    public OrderProducts checkOrderDetails() {
        for (Entry<Product, Integer> order : orders.entrySet()) {
            Product product = order.getKey();
            int purchaseCount = order.getValue();
            int lackQuantity = getLackQuantity(product, purchaseCount);
            int freeCount = getFreeCount(product, purchaseCount);

            updatePurchaseCount(order, lackQuantity, product, purchaseCount, freeCount);
        }
        return this;
    }

    private void updatePurchaseCount(Entry<Product, Integer> order, int lackQuantity, Product product,
                                     int purchaseCount,
                                     int freeCount) {
        if (lackQuantity > 0 && !inputView.inputNotAdaptedPromotionIsOk(product.getName(), lackQuantity)) {
            order.setValue(purchaseCount - lackQuantity);
        }
        if (freeCount > 0 && inputView.inputGetMoreFreeProductIsOk(product.getName(), freeCount)) {
            order.setValue(purchaseCount + freeCount);
        }
    }

    public Map<Product, Integer> getOrders() {
        return orders;
    }

    private int getFreeCount(Product product, int purchaseCount) {
        int freeCount = 0;
        if (product.isPromotional()) {
            PromotionProduct promotionProduct = (PromotionProduct) product;
            if (product.isPromotional() && promotionProduct.possibleGetMore(purchaseCount)) {
                freeCount = promotionProduct.confirmCanGetFreeProductCount(purchaseCount);
            }
        }
        return freeCount;
    }

    private int getLackQuantity(Product product, int purchaseCount) {
        int lackQuantity = 0;
        if (product.isPromotional() && product.isLackQuantity(purchaseCount)) {
            int deducted = product.insufficientNumberOfProducts(purchaseCount);
            PromotionProduct promotionProduct = (PromotionProduct) product;
            lackQuantity = promotionProduct.confirmLackQuantity(purchaseCount) + deducted;
        }
        return lackQuantity;
    }
}
