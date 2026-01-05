package bridge.exception;

public enum ErrorMessage {

    DAY_OUT_OF_RANGE("유효한 날짜 범위가 아닙니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
