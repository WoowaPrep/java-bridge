package bridge.view;

import bridge.exception.BridgeException;
import bridge.exception.ErrorMessage;

public class InputParser {

    private static final String NUMERIC_REGEX = "\\d+";

    private static final int MIN_BRIDGE_LENGTH = 3;
    private static final int MAX_BRIDGE_LENGTH = 20;

    public int parseBridgeSize(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
        validateLength(input);
        return Integer.parseInt(input);
    }

    private void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw BridgeException.from(ErrorMessage.EMPTY_INPUT);
        }
    }

    private void validateNumeric(String input) {
        if (!input.matches(NUMERIC_REGEX)) {
            throw BridgeException.from(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }

    private void validateLength(String input) {
        int bridgeLength = Integer.parseInt(input);
        if (bridgeLength < MIN_BRIDGE_LENGTH || bridgeLength > MAX_BRIDGE_LENGTH) {
            throw BridgeException.from(ErrorMessage.INVALID_BRIDGE_LENGTH);
        }
    }
}
