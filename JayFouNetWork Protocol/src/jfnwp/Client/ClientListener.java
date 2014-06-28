package jfnwp.Client;

import java.net.Socket;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Client.Interfaces.Observable;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;
import jfnwp.Implementation.Message;
import jfnwp.Services.MessageService;

public class ClientListener implements Observable {

	private static Logger logger = LogManager.getLogger(ClientListener.class
			.getName());
	private Socket sock;
	private ObservableData infoRpsls = new ObservableData();
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public ClientListener(Socket s) {
		sock = s;
	}

	public void run() {
		try
		{
		logger.info("Entrée dans le ThreadClient");
		MessageService m = new MessageService(sock);
		Message mess = new Message();

		while (true) {
			while (mess == null) {
				mess = m.ReadMessage();
			}
			switch (mess.getId()) {

			case 3:
				logger.info("msg end game reçu");
				infoRpsls.setEndGame("quit");
				updateObserver();
				break;
			
			case 4:
				logger.info("Message move reçu");
				infoRpsls.setMsgId("04");
				infoRpsls.setInfo("It's your turn ! Please make a move.");
				updateObserver();
				break;

			case 5:
				logger.info("Message result reçu "+mess.getData());
				infoRpsls.setResult(mess.getData());
				updateObserver();
				infoRpsls.setResult("");
				break;
				
			case 9:
				logger.info("Message sendAdress reçu");
				infoRpsls.setChatData(mess.getData());
				updateObserver();
				break;

			case 12:
				logger.info("Message wait reçu");
				infoRpsls.setMsgId("12");
				infoRpsls.setInfo(mess.getData());
				updateObserver();
				break;
				
			case 14:
				logger.info("error msg reçu");
				infoRpsls.setMsgId("14");
				infoRpsls.setInfo(mess.getData());
				updateObserver();
				break;
				
			case 17:
				logger.info("info msg reçu");
				infoRpsls.setGameInfo(mess.getData());
				updateObserver();
				break;

			default:
				break;
			}

			mess = null;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("fail " +e.getMessage());
		}
	}

	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void delObserver() {
		this.listObserver = new ArrayList<Observer>();
	}

	public void updateObserver() {
		for (Observer obs : this.listObserver) {
			obs.update(infoRpsls);
		}
	}
}
