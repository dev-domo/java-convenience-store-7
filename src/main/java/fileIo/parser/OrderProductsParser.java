package fileIo.parser;

import static constants.ErrorMessage.INVALID_ORDER;
import static constants.ErrorMessage.NOT_EXISTS_PRODUCT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.OrderProducts;
import store.product.Product;
import store.product.Products;

public class OrderProductsParser implements Parser {

    private static final String REGEX = "\\[([가-힣]+)-(\\d+)]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final Products products;

    public OrderProductsParser(Products products) {
        this.products = products;
    }

    @Override
    public OrderProducts parse(List<String> lines) {
        Map<Product, Integer> orders = new HashMap<>();
        Matcher matcher;

        for (String line : lines) {
            matcher = PATTERN.matcher(line);
            checkInputFormat(matcher);
            Product product = products.findProductByName(extractName(matcher));
            checkExistsProduct(product);
            orders.put(product, extractPurchaseCount(matcher));
        }
        return new OrderProducts(orders);
    }

    private String extractName(Matcher matcher) {
        return matcher.group(1);
    }

    private int extractPurchaseCount(Matcher matcher) {
        return Integer.parseInt(matcher.group(2));
    }

    private void checkExistsProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException(NOT_EXISTS_PRODUCT.valueOf());
        }
    }

    private void checkInputFormat(Matcher matcher) {
        if (!matcher.find()) {
            throw new IllegalArgumentException(INVALID_ORDER.valueOf());
        }
    }
}
