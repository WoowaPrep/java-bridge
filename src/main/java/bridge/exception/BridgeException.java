package bridge.exception;

public class BridgeException extends IllegalArgumentException {

    private BridgeException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static BridgeException from(ErrorMessage errorMessage) {
        return new BridgeException(errorMessage);
    }
}
