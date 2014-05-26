package jfnwp.Implementation;

public class Player {
	private String nickname;
	private int port;
	private String address;
	private int chatPort;
	private String chatAddress;
	
	public Player(String nickname, int port, String address, int chatPort, String chatAddress) {
		super();
		this.nickname = nickname;
		this.port = port;
		this.address = address;
		this.chatPort = chatPort;
		this.chatAddress = chatAddress;
	}
}