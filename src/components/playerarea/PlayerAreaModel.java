package components.playerarea;

public class PlayerAreaModel {
    private String playerName;
    private int playerNumber;
    private int gamesWon = 0;
    private long turnStartTime;
    private long gameStartTime;
    private long totalGameTime = 0;
    private boolean turnActive;
    
    public PlayerAreaModel(int playerNumber, String playerName) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    public int getGamesWon() {
        return gamesWon;
    }
    
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
    
    
    public long getCurrentTurnTime() {
        return System.currentTimeMillis() - turnStartTime;
    }
    
    public long getTotalGameTime() {
        return System.currentTimeMillis() - gameStartTime;
    }
    
    
    public void incrementGamesWon() {
        this.gamesWon++;
    }
    
    public void setTurnActive(boolean value) {
    	this.turnActive = value;
    }
    
    public String getTurnActive() {
    	if(turnActive) {
    		return playerName; 
    	}
    	else {
    		return "";
    	}
    }
}
