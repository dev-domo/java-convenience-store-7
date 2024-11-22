package fileIo;

import constants.ErrorMessage;
import constants.FilePaths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsReader implements ResourceReader {

    @Override
    public List<String> read() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FilePaths.PRODUCTS_FILE.valueOf()))) {
            addLines(bufferedReader, lines);
        } catch (IOException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FILE_PATH.valueOf());
        }
        return lines;
    }

    private void addLines(BufferedReader bufferedReader, List<String> lines) throws IOException {
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
    }
}
