package jfnwp.Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jfnwp.Services.MessageService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RefereeServer extends Thread {

	public static final int port = 9890;
	private static final int maxThread = 8;
	private Socket s;
	private static Logger logger = LogManager.getLogger(RefereeServer.class.getName());
	private static WaitingServer waitingServer;

	RefereeServer(Socket sock)
	{
		this.s = sock;
	}

	public static void main(String args[]) throws Exception
	{
		ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
		ServerSocket ssock = new ServerSocket(port);
		waitingServer = new WaitingServer();
		logger.info("Server runnning on port " + ssock.getLocalPort() + " and address " + ssock.getInetAddress());
		while (true) {
			Socket sock = ssock.accept();
			logger.info("Connection of " + sock.getInetAddress()); 
			//executorService.execute(new RefereeServer(sock));
		}
	}

	public void run()
	{
		MessageService m = new MessageService(s);
		m.Ok();
		//String mess = m.ReadMessage();
		//if(mess.getId() == 1){ // TODO : change this
			//Récupérer le type de jeu
		//}
	}
}
