package bridge;

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
        List<String> directions = createBridge(bridgeSize);
        String gameDirections = String.join("", directions);

        boolean isContinue;
        do{
            isContinue = readMoving(gameDirections, bridgeSize);
        } while (isContinue);

//        String bridgeGameResult = bridgeGame.createGameResult(gameDirections);
//        outputView.printResult(bridgeGameResult);
    }

    private List<String> createBridge(int bridgeSize) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        return new BridgeMaker(bridgeNumberGenerator).makeBridge(bridgeSize);
    }

    private int readBridgeSize() {
        return retry(() -> {
            String bridgeSizeInput = inputView.readBridgeSize();
            inputView.printNewLine();
            return inputParser.parseBridgeSize(bridgeSizeInput);
        });
    }

    private boolean readMoving(String gameDirections, int count) {
        String directions = "";
        for (int i = 0; i < count; i++) {
            String direction = readMovingDirection();
            directions += direction;
            String matchHistory = bridgeGame.createMatchHistory(gameDirections, directions);
            String mapResult = bridgeGame.move(gameDirections, matchHistory);
            outputView.printMap(mapResult);
            if (bridgeGame.retry(matchHistory)) {
                return handleRetry();
            }
        }
        return false;
    }

    private String readMovingDirection() {
        return retry(() -> {
            String movingDirection = inputView.readMoving();
            inputView.printNewLine();
            return inputParser.parseMovingDirection(movingDirection);
        });
    }

    private String readGameCommand() {
        return retry(() -> {
            String shouldRetry = inputView.readGameCommand();
            inputView.printNewLine();
            return inputParser.parseRetry(shouldRetry);
        });
    }

    private boolean handleRetry() {
        String command = readGameCommand();
        return command.equals("R");
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
