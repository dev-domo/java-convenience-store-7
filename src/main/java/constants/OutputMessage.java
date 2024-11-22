package constants;

public enum OutputMessage {

    WELCOME_MESSAGE("안녕하세요. W편의점입니다."),
    CURRENT_PRODUCTS_STATE_MESSAGE("현재 보유하고 있는 상품입니다.\n");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
