package bridge.view;

import bridge.domain.GameStatus;
import bridge.exception.BridgeException;
import bridge.exception.ErrorMessage;

public class InputParser {

    private static final String NUMERIC_REGEX = "\\d+";

    private static final int MIN_BRIDGE_LENGTH = 3;
    private static final int MAX_BRIDGE_LENGTH = 20;

    private static final String GAME_RESTART_COMMEND = "R";
    private static final String GAME_QUIT_COMMEND = "Q";
    private static final String MOVING_UP_COMMEND = "U";
    private static final String MOVING_DOWN_COMMEND = "D";

    public int parseBridgeSize(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
        validateLength(input);
        return Integer.parseInt(input);
    }

    public char parseMovingDirection(String input) {
        validateNotEmpty(input);
        validateDirection(input);
        return input.charAt(0);
    }

    public GameStatus parseGameCommend(String input) {
        validateNotEmpty(input);
        return validateGameCommend(input);
    }

    private GameStatus validateGameCommend(String input) {
        if (input.equals(GAME_RESTART_COMMEND)) return GameStatus.RESTART;
        if (input.equals(GAME_QUIT_COMMEND)) return GameStatus.QUIT;

        throw BridgeException.from(ErrorMessage.INVALID_RETRY);
    }

    private void validateDirection(String input) {
        if (input.equals(MOVING_UP_COMMEND) || input.equals(MOVING_DOWN_COMMEND)) {
            return;
        }
        throw BridgeException.from(ErrorMessage.INVALID_MOVING_DIRECTION);
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
