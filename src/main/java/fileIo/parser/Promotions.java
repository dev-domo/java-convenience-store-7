package fileIo.parser;

import java.util.List;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions(final List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findPromotionByName(String name) {
        return promotions.stream()
                .filter(promotion -> promotion.isSameName(name))
                .findFirst()
                .orElse(null);
    }
}
