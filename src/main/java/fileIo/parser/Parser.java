package fileIo.parser;

import java.util.List;

public interface Parser {

    String SEPARATOR = ",";
    String DATE_SEPARATOR = "-";

    Object parse(List<String> lines);
}
