package jfnwp.Server;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jfnwp.Exception.GameFullException;
import jfnwp.Exception.MoveException;
import jfnwp.Games.*;
import jfnwp.Implementation.Message;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;
import jfnwp.Services.MessageService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @version 1.0
 */
public class RefereeServer extends Thread {

	public static int port;
	private static final int maxThread = 30;
	private Socket s;
	private GameContext gc;
	private Player p;
	private static Logger logger = LogManager.getLogger(RefereeServer.class
			.getName());

	RefereeServer(Socket sock) {
		this.s = sock;
	}

	public static void main(String args[]) throws Exception {
		
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			logger.info("Argument Error " + e.getMessage());
			port = 9890;
		}
		
		ExecutorService executorService = Executors
				.newFixedThreadPool(maxThread);
		ServerSocket ssock = new ServerSocket(port);
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
					clazz = Class.forName("jfnwp.Games." + className);
					GameContext gac = Info.Instance.getExistingGame(clazz);
					if (gac != null) {
						logger.info("Start game reçu, join game existante");
						gc = gac;
						gc.getGame().addPlayerList(p);
						if (gc.getGame().isGameFull()) {
							m.Move();
						}
						else
						{
							m.Wait("Wait for another player to join game please");
						}
					} else {
						logger.info("Start game reçu, creation nouvelle game");
						IGame g = (IGame) clazz.newInstance();
						g.addPlayerList(p);
						gc = new GameContext(g, Info.Instance.getHost(), Info.Instance.getPort());
						Info.Instance.addGameList(gc);
						m.Wait("Wait for another player to join game please");
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
				Info.Instance.deleteGame(gc, p.getAddress());
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
				logger.info("Move reçu");
				try {
					Class c = Class.forName("jfnwp.Moves."
							+ gc.getGame().getClass().getSimpleName() + "Move");
					IMove move = (IMove) c.newInstance();
					move.deserialize(mess.getData());
					move.setPlayerIp(p.getAddress().toString());
					gc.applyMove(move);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 7:
				break;

			case 8:
				logger.info("GetAdress reçu");
				String s = gc.getHost() +";" +Integer.toString(gc.getPort()) +";";
				logger.info(s);
				m.SendAdress(s);
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
