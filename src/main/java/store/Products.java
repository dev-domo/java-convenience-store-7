package store;

import java.util.List;
import java.util.stream.Collectors;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return products.stream()
                .map(Product::toString)
                .collect(Collectors.joining(LINE_CHANGE));
    }
}
