package bridge;

public class GameFinalStatus {

    private String finalBridge;
    private GameStatus gameStatus;
    private int tryCount;

    public GameFinalStatus(String finalBridge, GameStatus gameStatus, int tryCount) {
        this.finalBridge = finalBridge;
        this.gameStatus = gameStatus;
        this.tryCount = tryCount;
    }

    public String getFinalBridge() {
        return finalBridge;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getTryCount() {
        return tryCount;
    }
}
