package store.membership;

public class Membership {

    private static final double DISCOUNT_RATE = 0.3;
    private static final int MAX_BOUND = 8_000;

    public int calculateDiscount(int total) {
        return Math.min((int) (total * DISCOUNT_RATE), MAX_BOUND);
    }
}
