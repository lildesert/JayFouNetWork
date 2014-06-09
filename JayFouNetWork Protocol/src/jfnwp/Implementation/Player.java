package jfnwp.Implementation;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player {
	private String nickname;
	private InetAddress address;
	private int chatPort;
	private String chatAddress;
	private Socket s;
	private EnumGame game;
	
	public Player(String data, Socket s){
		String[] tab = data.split(";");
		this.nickname = tab[0];
		try {
			this.address = InetAddress.getByName(tab[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.s = s;
	}
	
	public Player(String nickname, Socket s, InetAddress a, int chatPort, String chatAddress) {
		this.nickname = nickname;
		this.s = s;
		this.address = a;
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