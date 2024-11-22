package constants;

public enum FilePaths {

    PRODUCTS_FILE("src/main/resources/products.md"),
    PROMOTIONS_FILE("src/main/resources/promotions.md");

    private final String path;

    FilePaths(final String path) {
        this.path = path;
    }

    public String valueOf() {
        return path;
    }
}
