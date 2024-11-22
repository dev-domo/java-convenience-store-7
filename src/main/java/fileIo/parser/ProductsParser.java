package fileIo.parser;

import java.util.ArrayList;
import java.util.List;
import store.Promotion;
import store.PromotionProduct;
import store.Promotions;
import store.product.Product;
import store.product.Products;

public class ProductsParser implements Parser {

    private static final int NAME_FIELD = 0;
    private static final int PRICE_FIELD = 1;
    private static final int QUANTITY_FILED = 2;
    private static final int PROMOTION_FILED = 3;

    private final Promotions promotions;

    public ProductsParser(final Promotions promotions) {
        this.promotions = promotions;
    }

    @Override
    public Products parse(List<String> lines) {
        return new Products(createProducts(lines));
    }

    private List<Product> createProducts(List<String> lines) {
        List<Product> products = new ArrayList<>();
        for (String line : lines) {
            String[] information = line.split(SEPARATOR);
            addRegularProduct(information, products);
            addPromotionProduct(products, information);
        }
        return products;
    }

    private void addPromotionProduct(List<Product> products, String[] information) {
        products.add(new PromotionProduct(extractName(information), extractPrice(information),
                extractQuantity(information),
                extractPromotion(information)));
    }

    private void addRegularProduct(String[] information, List<Product> products) {
        products.add(
                new Product(extractName(information), extractPrice(information), extractQuantity(information)));
    }

    private String extractName(String[] information) {
        return information[NAME_FIELD];
    }

    private int extractPrice(String[] information) {
        return toInteger(information[PRICE_FIELD]);
    }

    private int extractQuantity(String[] information) {
        return toInteger(information[QUANTITY_FILED]);
    }

    private Promotion extractPromotion(String[] information) {
        return promotions.findPromotionByName(information[PROMOTION_FILED]);
    }

    private int toInteger(String text) {
        return Integer.parseInt(text);
    }
}
