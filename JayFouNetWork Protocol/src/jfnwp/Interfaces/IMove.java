package jfnwp.Interfaces;

public interface IMove {
	void deserialize(String s);
	void serialize(String s);
	String getSerializedData();
	void setPlayerIp(String ip);
	String getPlayerIp();
	String getMoveResult();
	void setMoveResult(String s);
}
