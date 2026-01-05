package bridge.exception;

public enum ErrorMessage {

    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),

    INVALID_BRIDGE_LENGTH("다리의 길이는 2이상 20이하 어야 합니다."),
    INVALID_MOVING_DIRECTION("이동할 방향은 U 또는 D여야 합니다."),
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
