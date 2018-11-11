package mytetris;

public class GameUtil {

    private static GameState myGameState;
    private static GameState opponentState;
    
    public static GameState getMyGameState() {
        return myGameState;
    }
    
    public static GameState getOppGameState() {
        return opponentState;
    }
}
