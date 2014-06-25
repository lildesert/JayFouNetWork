package jfnwp.Client;

import java.net.Socket;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Client.Interfaces.Observable;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.Message;
import jfnwp.Services.MessageService;

public class InfoServer implements Observable {

	private static Logger logger = LogManager.getLogger(InfoServer.class
			.getName());
	
	private Socket sock;
	private String info = "";
	private ArrayList<Observer> listObservateur = new ArrayList<Observer>();
	
	public InfoServer(Socket s)
	{
		sock = s;
	}

	public void run() {
		logger.info("Entrée dans le ThreadClient");
		MessageService m = new MessageService(sock);
		Message mess = new Message();

		while (true) {
			while (mess == null) {
				mess = m.ReadMessage();
			}
			switch (mess.getId()) {

			case 4:
				info = "It's your turn ! Please make a move.";
				this.updateObserver();
				break;

			case 5:
				break;

			case 12:
				logger.info("Message wait reçu");
				if(mess.getData().length() != 0)
				{
					info = mess.getData();
				}
				else
				{
				info = "WAIT";
				}
				this.updateObserver();
				break;

			default:
				break;
			}

			mess = null;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addObserver(Observer obs) {
		this.listObservateur.add(obs);
	}

	public void delObserver() {
		this.listObservateur = new ArrayList<Observer>();
	}

	public void updateObserver() {
		for (Observer obs : this.listObservateur)
			obs.update(this.info);
	}
}
