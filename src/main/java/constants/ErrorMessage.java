package constants;

public enum ErrorMessage implements Error {

    INVALID_FILE_PATH("파일 경로를 찾을 수 없습니다."),
    INVALID_INPUT(error + "잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_ORDER(error + "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    NOT_EXISTS_PRODUCT(error + "존재하지 않는 상품입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
