package jfnwp.Implementation;

import java.net.Socket;

/**
 * The representation of a player. 
 * @version 1.0
 */
public class Player {
	private String nickname;
	private String ipAddress;
	private Socket sock;

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

	public Socket getSock() {
		return sock;
	}
}
