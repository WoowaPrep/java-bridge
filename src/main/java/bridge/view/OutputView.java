package bridge.view;

import bridge.domain.GameFinalStatus;
import bridge.domain.GameStatus;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String BRIDGE_CROSS_GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";
    private static final String GAME_RESULT_MESSAGE = "최종 게임 결과";
    private static final String GAME_SUCCESS_MESSAGE = "게임 성공 여부: %s%n";
    private static final String GAME_TRY_COUNT_MESSAGE = "총 시도한 횟수: %d%n";

    public void printBridgeCrossGameHeader() {
        System.out.println(BRIDGE_CROSS_GAME_START_MESSAGE);
        printNewLine();
    }
    public void printMap(String roundBridge) {
        System.out.println(roundBridge);
        printNewLine();
    }

    public void printResult(GameFinalStatus gameFinalStatus) {
        System.out.println(GAME_RESULT_MESSAGE);
        System.out.println(gameFinalStatus.getFinalBridge());
        printNewLine();
        String gameResult = getGameResultText(gameFinalStatus);
        System.out.printf(GAME_SUCCESS_MESSAGE, gameResult);
        System.out.printf(GAME_TRY_COUNT_MESSAGE, gameFinalStatus.getTryCount());
    }

    private String getGameResultText(GameFinalStatus gameFinalStatus) {
        if (gameFinalStatus.getGameStatus() == GameStatus.COMPLETE) {
            return "성공";
        }
        return "실패";
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
