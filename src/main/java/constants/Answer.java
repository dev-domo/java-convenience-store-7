package constants;

public enum Answer {

    YES("Y"),
    NO("N");

    private final String message;

    Answer(final String message) {
        this.message = message;
    }

    public static boolean convertToCondition(String text) {
        if (text.equals(YES.message)) {
            return true;
        }
        if (text.equals(NO.message)) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.valueOf());
    }
}
