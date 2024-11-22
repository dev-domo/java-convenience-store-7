package fileIo.parser;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int buy;
    private final int get;

    public Promotion(final String name, final int buy, final int get, final LocalDate startDate,
                     final LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
