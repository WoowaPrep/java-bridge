package bridge;

import bridge.view.InputParser;
import bridge.view.InputView;
import bridge.view.OutputView;

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
        return 0;
    }
}
