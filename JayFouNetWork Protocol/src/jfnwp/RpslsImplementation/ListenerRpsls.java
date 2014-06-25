package jfnwp.RpslsImplementation;

import java.net.Socket;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Client.Interfaces.Observable;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.Data;
import jfnwp.Implementation.Message;
import jfnwp.Services.MessageService;

public class ListenerRpsls implements Observable {

	private static Logger logger = LogManager.getLogger(ListenerRpsls.class
			.getName());
	private Socket sock;
	private Data infoRpsls = new Data();
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public ListenerRpsls(Socket s) {
		sock = s;
	}

	public void run() {
		try
		{
		logger.info("Entr�e dans le ThreadClient");
		MessageService m = new MessageService(sock);
		Message mess = new Message();

		while (true) {
			while (mess == null) {
				mess = m.ReadMessage();
			}
			switch (mess.getId()) {

			case 4:
				logger.info("Message move re�u");
				if(mess.getData() != null)
				{
					infoRpsls.setResult(mess.getData());
				}
				infoRpsls.setMsgId("04");
				infoRpsls.setInfo("It's your turn ! Please make a move.");
				updateObserver();
				logger.info("fin switch 4");
				break;

			case 5:
				break;

			case 12:
				logger.info("Message wait re�u");
				infoRpsls.setMsgId("12");
				infoRpsls.setInfo(mess.getData());
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
			logger.error(e.getMessage());
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