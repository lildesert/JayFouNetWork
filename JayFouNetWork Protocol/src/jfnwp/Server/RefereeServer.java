package jfnwp.Server;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jfnwp.Implementation.Message;
import jfnwp.Implementation.Player;
import jfnwp.Services.MessageService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RefereeServer extends Thread {

	public static final int port = 9890;
	private static final int maxThread = 8;
	private Socket s;
	private static Logger logger = LogManager.getLogger(RefereeServer.class
			.getName());
	private static WaitingServer waitingServer;

	RefereeServer(Socket sock) {
		this.s = sock;
	}

	public static void main(String args[]) throws Exception {
		ExecutorService executorService = Executors
				.newFixedThreadPool(maxThread);
		ServerSocket ssock = new ServerSocket(port);
		waitingServer = new WaitingServer();
		logger.info("Server runnning on port " + ssock.getLocalPort()
				+ " and address " + ssock.getInetAddress());
		while (true) {
			Socket sock = ssock.accept();
			logger.info("Connection of " + sock.getInetAddress());
			executorService.execute(new RefereeServer(sock));
		}
	}

	public void run() {
		MessageService m = new MessageService(s);
		Message mess = m.ReadMessage();
		if (mess.getId() == 1) {
			Player p = new Player(mess.getData(), s);
			InfoSingleton.getInstance().addPlayerList(p);
			System.out.println("FROM CLIENT: " + mess.ToString());
			m.Ok();
		} else {
			m.Error("Bad connect request");
		}

		while (true) {
			mess = m.ReadMessage();
			switch (mess.getId()) {
			
				case 1:
					break;
					
				case 2:
					break;
					
				case 3:
					break;
					
				case 4:
					break;
					
				case 5:
					break;
					
				case 6:
					break;
					
				case 7:
					break;
					
				case 8:
					break;
					
				case 9:
					break;
					
				case 10:
					break;
					
				case 11:
					break;
					
				case 12:
					break;
					
				case 13:
					break;
					
				case 14:
					m.SendGames();
					break;
					
				case 15:
					break;
					
				case 16:
					break;
					
				default :
					break;
			}
		}
	}
}
