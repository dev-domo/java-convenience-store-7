    package store.domain;

    public class Order {
        private String productName;
        private int unitPrice;
        private int quantity;
        private int promotionBonus;
        private int totalCost;
        private int promotionDiscount = 0;
        private int membershipDiscount = 0;

        public Order(String productName, int unitPrice, int quantity, int promotionBonus) {
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.promotionBonus = promotionBonus;
            this.totalCost = unitPrice * quantity;
        }

        public String getProductName() {
            return productName;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPromotionBonus() {
            return promotionBonus;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public void applyPromotionDiscount(int discount) {
            this.promotionDiscount = discount;
        }

        public void applyMembershipDiscount(int discount) {
            this.membershipDiscount = discount;
        }

        public int getFinalPayable() {
            return totalCost - promotionDiscount - membershipDiscount;
        }
    }
