package jfnwp.Client;

import java.net.Socket;

/**
 * A generic client. 
 * New client must inherit from this class. 
 * @version 1.0
 */
public class Client {

	protected Socket sock;
	protected String name;
	
	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Client(Socket s, String n)
	{
		sock = s;
		name = n;
	}
}
