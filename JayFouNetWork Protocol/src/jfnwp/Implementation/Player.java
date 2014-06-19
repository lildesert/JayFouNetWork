package jfnwp.Implementation;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player {
	private String nickname;
	private InetAddress address;
	private int chatPort;
	private String chatAddress;
	private Socket sock;
	private String state;

	private EnumGame game;
	
	public Player(String data, Socket s){
		String[] tab = data.split(";");
		this.nickname = tab[0];
		try {
			this.address = InetAddress.getByName(tab[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.sock = s;
	}
	
	public Player(String nickname, Socket s, InetAddress a, int chatPort, String chatAddress) {
		this.nickname = nickname;
		this.sock = s;
		this.address = a;
		this.chatPort = chatPort;
		this.chatAddress = chatAddress;
	}
	
	public InetAddress getAddress() {
		return address;
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