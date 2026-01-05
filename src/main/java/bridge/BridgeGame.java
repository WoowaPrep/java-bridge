package bridge;

public class BridgeGame {

    private BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeGame(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public String move(String gameDirections, String matchHistory) {
        int round = matchHistory.length();

        String upperMap = makeMap(gameDirections, matchHistory, round, 'U');
        String lowerMap = makeMap(gameDirections, matchHistory, round, 'D');
        return upperMap + lowerMap;
    }

    private String makeMap(String directionHistory, String matchHistory, int round, char direction) {
        StringBuilder map = new StringBuilder("[");

        for (int i = 0; i < round; i++) {
            mapBuild(directionHistory, matchHistory, direction, i, map);

            if (i < round - 1) {
                map.append("|");
            }
        }

        map.append("]").append("%n");
        return map.toString();
    }

    private void mapBuild(String directionHistory, String matchHistory,
            char direction, int i, StringBuilder map) {
        char marker = matchHistory.charAt(i);
        char whatDir = directionHistory.charAt(i);

        if (whatDir == direction) {
            map.append(" ").append(marker).append(" ");
            return;
        }
        map.append("   ");
    }

    public String createMatchHistory(String gameDirections, String directions) {
        StringBuilder matchHistory = new StringBuilder();

        for (int i = 0; i < directions.length(); i++) {
            if (gameDirections.charAt(i) == directions.charAt(i)) {
                matchHistory.append("O");
                continue;
            }
            matchHistory.append("X");
        }

        return matchHistory.toString();
    }

    public boolean retry(String matchHistory) {
        return matchHistory.contains("X");
    }
}
