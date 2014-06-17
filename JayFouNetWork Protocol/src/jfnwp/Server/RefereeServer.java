package jfnwp.Server;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jfnwp.Exception.GameFullException;
import jfnwp.Games.*;
import jfnwp.Implementation.Message;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IGame;
import jfnwp.Services.MessageService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RefereeServer extends Thread {

	public static final int port = 9890;
	private static final int maxThread = 8;
	private Socket s;
	private GameContext gc;
	private Player p;
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
		Message mess = new Message();

		while (true) {
			while (mess == null) {
				mess = m.ReadMessage();
			}
			switch (mess.getId()) {

			case 1:
				if (p != null) {
					m.Error("Client already connected");
				} else {
					p = new Player(mess.getData(), s);
					System.out.println("FROM CLIENT: " + mess.ToString());
					m.Ok();
				}
				break;

			case 2:
				String className = mess.getData();
				Class clazz = null;
				try {
					clazz = Class.forName("jfnwp.Games."+className);
					GameContext gac = Info.Instance.getExistingGame(clazz);
					if (gac != null) {
						gc = gac;
						gc.getGame().addPlayerList(p);
						if(gc.getGame().isGameFull())
						{
							m.Move(null);
						}
					} else {
						IGame g = (IGame) clazz.newInstance();
						g.addPlayerList(p);
						gc = new GameContext(g);
						Info.Instance.addGameList(gc);
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (GameFullException e) {
					e.printStackTrace();
				}

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
				break;

			case 15:
				m.SendGames();
				break;

			case 16:
				break;

			default:
				break;
			}

			mess = null;
		}
	}
}
