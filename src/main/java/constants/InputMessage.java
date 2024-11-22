package constants;

public enum InputMessage {

    INPUT_PRODUCT_AND_COUNT_MESSAGE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    INPUT_ADAPT_MEMBERSHIP_MESSAGE("멤버십 할인을 받으시겠습니까? (Y/N)"),
    INPUT_RETRY_MESSAGE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    PRESENT("현재 "),
    WORDING("은(는) "),
    NOT_ADAPTED_PROMOTION_MESSAGE("개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    GET_MORE_FREE_PRODUCT_MESSAGE("개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");

    private final String message;

    InputMessage(final String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
