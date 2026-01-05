package bridge;

import bridge.view.InputParser;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class BridgeCross {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;

    public BridgeCross() {
        this(new InputView(), new OutputView(), new InputParser());
    }

    public BridgeCross(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void start() {
        outputView.printBridgeCrossGameHeader();
        int bridgeSize = readBridgeSize();

    }

    private int readBridgeSize() {
        return retry(() -> {
            String bridgeSizeInput = inputView.readBridgeSize();
            return inputParser.parseBridgeSize(bridgeSizeInput);
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
