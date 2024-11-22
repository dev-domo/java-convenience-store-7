package fileIo.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionsParser implements Parser {

    private static final int NAME_FIELD = 0;
    private static final int BUY_FIELD = 1;
    private static final int GET_FILED = 2;
    private static final int START_DATE_FILED = 3;
    private static final int END_DATE_FILED = 4;

    @Override
    public Promotions parse(List<String> lines) {
        return new Promotions(createPromotions(lines));
    }

    private List<Promotion> createPromotions(List<String> lines) {
        List<Promotion> promotions = new ArrayList<>();
        for (String line : lines) {
            String[] information = line.split(SEPARATOR);
            promotions.add(new Promotion(extractName(information), extractBuy(information), extractGet(information),
                    extractStartDate(information), extractEndDate(information)));
        }
        return promotions;
    }

    private LocalDate extractEndDate(String[] information) {
        return toDate(information[END_DATE_FILED].split(DATE_SEPARATOR));
    }

    private LocalDate extractStartDate(String[] information) {
        return toDate(information[START_DATE_FILED].split(DATE_SEPARATOR));
    }

    private int extractGet(String[] information) {
        return toInteger(information[GET_FILED]);
    }

    private int extractBuy(String[] information) {
        return toInteger(information[BUY_FIELD]);
    }

    private String extractName(String[] information) {
        return information[NAME_FIELD];
    }

    private LocalDate toDate(String[] dates) {
        return LocalDate.of(toInteger(dates[0]), toInteger(dates[1]), toInteger(dates[2]));
    }

    private int toInteger(String text) {
        return Integer.parseInt(text);
    }
}
