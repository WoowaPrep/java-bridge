package bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BridgeMaker {

    private static final String MOVING_UP_COMMEND = "U";
    private static final String MOVING_DOWN_COMMEND = "D";
    private static final int MOVING_UP_NUMBER = 1;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        List<String> bridge = new ArrayList<>();

        IntStream.range(0, size).forEach(i -> {
            int isUpper = bridgeNumberGenerator.generate();
            if (isUpper == MOVING_UP_NUMBER) {
                bridge.add(MOVING_UP_COMMEND);
                return;
            }
            bridge.add(MOVING_DOWN_COMMEND);
        });

        return bridge;
    }
}
