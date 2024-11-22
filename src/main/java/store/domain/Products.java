package store.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Products {
    private List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return products.stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
    }
}
