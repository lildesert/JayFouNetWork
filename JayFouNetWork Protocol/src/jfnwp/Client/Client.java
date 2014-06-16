package jfnwp.Client;

import java.net.Socket;

public class Client {

	protected Socket sock;
	protected String name;
	
	public void start()
	{}
	
	public Socket getSock() {
		return sock;
	}
	public void setSock(Socket sock) {
		this.sock = sock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
