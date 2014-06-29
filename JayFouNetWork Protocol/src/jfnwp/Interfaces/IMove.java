package jfnwp.Interfaces;

/**
 * Interface IMove.
 * @version 1.0
 */
public interface IMove {
	void deserialize(String s);
	void setPlayerIp(String ip);
	String getPlayerIp();
	String getMoveResult();
	void setMoveResult(String s);
}
