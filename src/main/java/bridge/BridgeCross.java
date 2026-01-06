package bridge;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeNumberGenerator;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.GameFinalStatus;
import bridge.domain.GameRoundStatus;
import bridge.domain.GameStatus;
import bridge.view.InputParser;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BridgeCross {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;
    private BridgeGame bridgeGame;

    public BridgeCross() {
        this(new InputView(), new OutputView(), new InputParser(), new BridgeGame(new BridgeRandomNumberGenerator()));
    }

    public BridgeCross(InputView inputView, OutputView outputView, InputParser inputParser, BridgeGame bridgeGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
        this.bridgeGame = bridgeGame;
    }

    public void start() {
        outputView.printBridgeCrossGameHeader();

        int bridgeSize = readBridgeSize();
        String gameDirections = createBridge(bridgeSize);

        GameFinalStatus gameFinalStatus = play(gameDirections, bridgeSize);
        outputView.printResult(gameFinalStatus);
    }

    private GameFinalStatus play(String gameDirections, int bridgeSize) {
        GameStatus gameStatus = GameStatus.COMPLETE;
        String finalRoundBridge = null;
        int tryCount = 0;

        do{
            GameRoundStatus status = readMoving(gameDirections, bridgeSize);
            String userDirections = status.getDirections();

            String matchHistory = bridgeGame.createMatchHistory(gameDirections, userDirections);
            String roundBridge = bridgeGame.createRound(gameDirections, matchHistory);
            outputView.printMap(roundBridge);

            if (status.getGameStatus() == GameStatus.RESTART) gameStatus = readGameCommand();
            if (gameStatus != GameStatus.RESTART) finalRoundBridge = roundBridge;
            tryCount++;

        } while (gameStatus == GameStatus.RESTART);

        return new GameFinalStatus(finalRoundBridge, gameStatus, tryCount);
    }

    private String createBridge(int bridgeSize) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        List<String> directions = new BridgeMaker(bridgeNumberGenerator).makeBridge(bridgeSize);
        return String.join("", directions);
    }

    private int readBridgeSize() {
        return retry(() -> {
            String bridgeSizeInput = inputView.readBridgeSize();
            inputView.printNewLine();
            return inputParser.parseBridgeSize(bridgeSizeInput);
        });
    }

    private GameRoundStatus readMoving(String gameDirections, int count) {
        StringBuilder userDirections = new StringBuilder();

        for (int i = 0; i < count; i++) {
            char userDirection = readMovingDirection();
            userDirections.append(userDirection);
            if (gameDirections.charAt(i) != userDirection) {
                return new GameRoundStatus(userDirections.toString(), GameStatus.RESTART);
            }
        }

        return new GameRoundStatus(userDirections.toString(), GameStatus.COMPLETE);
    }

    private char readMovingDirection() {
        return retry(() -> {
            String movingDirection = inputView.readMoving();
            inputView.printNewLine();
            return inputParser.parseMovingDirection(movingDirection);
        });
    }

    private GameStatus readGameCommand() {
        return retry(() -> {
            String commend = inputView.readGameCommand();
            inputView.printNewLine();
            return inputParser.parseGameCommend(commend);
        });
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
