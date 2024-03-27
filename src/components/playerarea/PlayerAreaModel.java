package components.playerarea;

/**
 * 
 * @author Kevin Mosekjaer
 */
public class PlayerAreaModel {
	
	/**
	 * Player Name
	 */
    private String playerName;
    
    /**
     * Int variables
     */
    private int playerNumber, piecesPlaced, gamesWon=0;
    
    /**
     * Variables for time
     */
    private long turnStartTime, gameStartTime, totalGameTime=0;
    
    /**
     * Constructor for PlayerAreaModel
     * @param playerNumber number
     * @param playerName name
     */
    public PlayerAreaModel(int playerNumber, String playerName) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
    }
    
    /**
     * Getter for Player name
     * @return name n
     */
    public String getPlayerName() {
        return playerName;
    }
    
    /**
     *Setter for player name
     * @param playerName name 
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    /**
     * Getter for player number
     * @return number num
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Setter for games won
     * @param playerNumber number
     */
    public void setGamesWon(int playerNumber) {
    	if(this.playerNumber == playerNumber) {
    		gamesWon++;
    	} else {
    		return;
    	}
    }
    
    /**
     * GEtter for games won
     * @return games won
     */
    public int getGamesWon() {
        return gamesWon;
    }
    
    /**
     * Setter for game pieces placed
     * @param piecesPlaced num
     */
    public void setPiecesPlaced(int piecesPlaced) {
    	this.piecesPlaced = piecesPlaced;
    }
    
    /**
     * Getter for game pieces placed
     * @return game pieces placed
     */
    public int getPiecesPlaced() {
    	return piecesPlaced;
    }
    
    /**
     * Getter for current turn time
     * @return start time
     */
    public long getCurrentTurnTime() {
        return System.currentTimeMillis() - turnStartTime;
    }
    
    /**
     * Getter for total game time
     * @return game start time
     */
    public long getTotalGameTime() {
        return System.currentTimeMillis() - gameStartTime;
    } 

}
