package bridge;

import java.util.stream.IntStream;

public class BridgeGame {

    private final static String NEW_LINE = System.lineSeparator();
    public static final String SPACE = " ";

    private static final String MATCH_MARKER = "O";
    private static final String UN_MATCH_MARKER = "X";

    private static final String BRIDGE_START_MARKER = "[";
    private static final String BRIDGE_END_MARKER = "]";
    public static final String BRIDGE_PIPE = "|";

    private static final char MOVING_UP_COMMEND = 'U';
    private static final char MOVING_DOWN_COMMEND = 'D';

    private BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeGame(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public String createRound(String gameDirections, String matchHistory) {
        int round = matchHistory.length();

        String upperBridge = makeBridge(gameDirections, matchHistory, round, MOVING_UP_COMMEND);
        String lowerBridge  = makeBridge(gameDirections, matchHistory, round, MOVING_DOWN_COMMEND);
        return upperBridge + lowerBridge;
    }

    private String makeBridge(String directionHistory, String matchHistory, int round, char direction) {
        StringBuilder map = new StringBuilder(BRIDGE_START_MARKER);

        IntStream.range(0, round).forEach(i -> {
            mapBuild(directionHistory, matchHistory, direction, i, map);
            if (i < round - 1) {
                map.append(BRIDGE_PIPE);
            }
        });

        map.append(BRIDGE_END_MARKER).append(NEW_LINE);
        return map.toString();
    }

    private void mapBuild(String directionHistory, String matchHistory,
            char direction, int i, StringBuilder map) {
        char marker = matchHistory.charAt(i);
        char whatDir = directionHistory.charAt(i);

        if (whatDir == direction) {
            map.append(SPACE).append(marker).append(SPACE);
            return;
        }
        map.append(SPACE).append(SPACE).append(SPACE);
    }

    public String createMatchHistory(String gameDirections, String directions) {
        StringBuilder matchHistory = new StringBuilder();
        int movingCount = directions.length();

        IntStream.range(0, movingCount).forEach(i -> {
            if (gameDirections.charAt(i) == directions.charAt(i)) {
                matchHistory.append(MATCH_MARKER);
                return;
            }
            matchHistory.append(UN_MATCH_MARKER);
        });

        return matchHistory.toString();
    }

    public void retry(String input) {
    }
}
