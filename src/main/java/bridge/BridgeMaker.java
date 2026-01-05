package bridge;

import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        String bridge = "";
        for (int i = 0; i < size; i++) {
            makeBridgeStep(bridge);
        }

        return List.of(bridge);
    }

    private void makeBridgeStep(String bridge) {
        int isUpper = bridgeNumberGenerator.generate();
        if (isUpper == 1) {
            bridge += "U";
            return;
        }
        bridge += "D";
    }
}
