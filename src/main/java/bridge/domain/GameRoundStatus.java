package bridge.domain;

public class GameRoundStatus {

    private String directions;
    private GameStatus gameStatus;

    public GameRoundStatus(String directions, GameStatus gameStatus) {
        this.directions = directions;
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String getDirections() {
        return directions;
    }
}
