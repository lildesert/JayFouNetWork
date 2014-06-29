package jfnwp.Implementation;

/**
 * 
 * @version 1.0
 */
public class ObservableData {

	private String info;
	private String result;
	private String msgId;
	private String chatData;
	private String gameInfo;
	private String endGame;
	
	public String getEndGame() {
		return endGame;
	}

	public void setEndGame(String endGame) {
		this.endGame = endGame;
	}

	public String getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(String gameInfo) {
		this.gameInfo = gameInfo;
	}

	public String getChatData() {
		return chatData;
	}

	public void setChatData(String chatData) {
		this.chatData = chatData;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public String getResult() {
		return result;
	}
}
