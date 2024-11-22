package store;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import store.domain.Order;
import store.domain.Orders;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;
import store.utils.Parser;

public class Application {
    public static void main(String[] args) {
        Map<String, Promotion> promotions = Parser.parsePromotions("promotions.md");
        List<Product> products = Parser.parseProducts("products.md", promotions);

        boolean shopping = true;

        while (shopping) {
            System.out.println("안녕하세요. W편의점입니다.");
            System.out.println("현재 보유하고 있는 상품입니다.");
            Products stocks = new Products(products);
            System.out.println(stocks);

            System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
            String input = Console.readLine();

            Orders orders = new Orders();
            boolean validPurchase = true;

            String[] items = input.replace("[", "").replace("]", "").split(",");
            for (String item : items) {
                String[] parts = item.split("-");
                String productName = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                // 상품 검색
                Product product = stocks.getProducts().stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + productName));

                if (product.getQuantity() < quantity) {
                    System.out.println("현재 재고가 부족합니다. 구매 가능한 수량은 " + product.getQuantity() + "개입니다.");
                    validPurchase = false;
                    break;
                }

                int nonPromoQuantity = product.calculateNonPromoQuantity(quantity);
                if (nonPromoQuantity > 0) {
                    System.out.println("현재 " + productName + " " + nonPromoQuantity + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
                    String confirmation = Console.readLine();
                    if (!confirmation.equalsIgnoreCase("Y")) {
                        validPurchase = false;
                        break;
                    }
                }

                int promotionBonus = product.getPromotion() != null ? product.getPromotion().calculateBonus(quantity - nonPromoQuantity) : 0;
                orders.addOrder(new Order(productName, (int) product.getPrice(), quantity, promotionBonus));
                product.decreaseQuantity(quantity); // 재고 차감
            }

            if (!validPurchase) continue;

            System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
            boolean applyMembershipDiscount = Console.readLine().equalsIgnoreCase("Y");

            System.out.println(orders.generateReceipt(applyMembershipDiscount));

            System.out.println("\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
            shopping = Console.readLine().equalsIgnoreCase("Y");
        }
    }
}
