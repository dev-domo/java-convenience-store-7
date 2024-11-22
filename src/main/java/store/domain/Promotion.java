package store.domain;

public class Promotion {
    private String name;
    private int condition;
    private int bonus;

    public Promotion(String name, int condition, int bonus) {
        this.name = name;
        this.condition = condition;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public int getCondition() {
        return condition;
    }

    public int getBonus() {
        return bonus;
    }

    public int calculateBonus(int quantity) {
        return (quantity / condition) * bonus;
    }
}
