package jfnwp.Implementation;

import java.net.Socket;

public class Player {
	private String nickname;
	private String ipAddress;
	private Socket sock;

	private EnumGame game;

	public Player(String data, Socket s) {
		String[] tab = data.split(";");
		this.nickname = tab[0];
		this.ipAddress = tab[1];
		this.sock = s;
	}

	public String getAddress() {
		return ipAddress;
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

	public Socket getSock() {
		return sock;
	}
}