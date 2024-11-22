package constants;

public enum ErrorMessage {

    INVALID_FILE_PATH("파일 경로를 찾을 수 없습니다."),
    INVALID_INPUT("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String valueOf() {
        return message;
    }
}
