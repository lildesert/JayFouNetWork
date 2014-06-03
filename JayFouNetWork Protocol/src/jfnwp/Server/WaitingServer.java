package jfnwp.Server;

import java.util.Iterator;
import java.util.LinkedList;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Implementation.Player;

public class WaitingServer {
	
	private LinkedList<Player> waitingList;
	private Logger logger;
	
	public WaitingServer() {
		waitingList = new LinkedList<Player>();
		logger = LogManager.getLogger(RefereeServer.class.getName());
	}
	
	public void addPlayer(Player p){
		waitingList.addLast(p);
		logger.info("Adding Player on waiting list : " + p.getNickname() + " " + p.getGame());
	}
	
	public Player getOpponent() {
		while(true){
			//For each player of the beginning of the list, find an opponent
			Iterator it = waitingList.iterator();
			int i = 0;
			while(it.hasNext()){
				Player p1 = (Player) it.next();
				Iterator it2 = waitingList.iterator();
				int j = 0;
				while(it2.hasNext()){
					if(i != j){
						Player p2 = (Player) it.next();
						if(p1.getGame() == p2.getGame()){
							//Create the game
						}
					}
					j++;
				}
				i++;
			}
		}
	}
}
