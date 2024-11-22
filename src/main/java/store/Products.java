package store;

import java.util.List;
import java.util.stream.Collectors;

public class Products {

    private static final String LINE_CHANGE = "\n";

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Product findProductByName(String name) {
        return products.stream().filter(product -> product.isSameName(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return products.stream()
                .map(Product::toString)
                .collect(Collectors.joining(LINE_CHANGE));
    }
}
