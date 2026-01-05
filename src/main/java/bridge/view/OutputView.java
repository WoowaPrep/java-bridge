package bridge.view;

import bridge.BridgeGame;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String BRIDGE_CROSS_GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";

    public void printBridgeCrossGameHeader() {
        System.out.println(BRIDGE_CROSS_GAME_START_MESSAGE);
        printNewLine();
    }
    public void printMap(String mapResult) {
        System.out.println(mapResult);
        printNewLine();
    }

    public void printResult(String result) {

    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
