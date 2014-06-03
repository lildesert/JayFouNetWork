package jfnwp.Implementation;

import java.net.Socket;

public class Player {
	private String nickname;
	private int port;
	private String address;
	private int chatPort;
	private String chatAddress;
	private Socket s;
	private EnumGame game;
	
	public Player(Socket s, EnumGame game){
		super();
		this.nickname = nickname;
		this.s = s;
		this.game = game;
	}
	
	public Player(String nickname, int port, Socket s, String address, int chatPort, String chatAddress) {
		super();
		this.nickname = nickname;
		this.port = port;
		this.s = s;
		this.address = address;
		this.chatPort = chatPort;
		this.chatAddress = chatAddress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public EnumGame getGame() {
		return game;
	}

	public void setGame(EnumGame game) {
		this.game = game;
	}
}