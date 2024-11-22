package store;

import java.util.Map;

public class OrderProducts {

    private final Map<Product, Integer> orders;

    public OrderProducts(Map<Product, Integer> orders) {
        this.orders = orders;
    }
}
