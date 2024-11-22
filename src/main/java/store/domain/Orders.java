package store.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Orders {
    private List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int calculateTotalCost() {
        return orders.stream()
                .mapToInt(Order::getTotalCost)
                .sum();
    }

    public int calculateTotalPromotionDiscount() {
        return orders.stream()
                .mapToInt(order -> order.getPromotionBonus() * order.getUnitPrice())
                .sum();
    }

    public int calculateTotalMembershipDiscount(boolean applyMembershipDiscount) {
        if (!applyMembershipDiscount) {
            return 0;
        }
        return 0;
    }

    public int calculateFinalAmount(boolean applyMembershipDiscount) {
        int totalCost = calculateTotalCost();
        int totalPromotionDiscount = calculateTotalPromotionDiscount();
        int totalMembershipDiscount = calculateTotalMembershipDiscount(applyMembershipDiscount);
        return totalCost - totalPromotionDiscount - totalMembershipDiscount;
    }

    public String generateReceipt(boolean applyMembershipDiscount) {
        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.KOREA);

        StringBuilder receipt = new StringBuilder();
        receipt.append("============== W 편의점 ================\n");
        receipt.append("상품명\t수량\t금액\n");

        for (Order order : orders) {
            receipt.append(String.format("%s\t%d\t%s\n",
                    order.getProductName(),
                    order.getQuantity(),
                    currencyFormat.format(order.getTotalCost())));
        }

        receipt.append("============= 증    정 ==============\n");
        for (Order order : orders) {
            if (order.getPromotionBonus() > 0) {
                receipt.append(String.format("%s\t%d\n", order.getProductName(), order.getPromotionBonus()));
            }
        }
        receipt.append("=====================================\n");

        int totalCost = calculateTotalCost();
        int totalPromotionDiscount = calculateTotalPromotionDiscount();
        int totalMembershipDiscount = calculateTotalMembershipDiscount(applyMembershipDiscount);
        int finalAmount = calculateFinalAmount(applyMembershipDiscount);

        receipt.append(String.format("총구매액\t\t%s\n", currencyFormat.format(totalCost)));
        receipt.append(String.format("행사할인\t\t-%s\n", currencyFormat.format(totalPromotionDiscount)));
        receipt.append(String.format("멤버십할인\t\t-%s\n", currencyFormat.format(totalMembershipDiscount)));
        receipt.append(String.format("내실돈\t\t%s\n", currencyFormat.format(finalAmount)));

        return receipt.toString();
    }
}
