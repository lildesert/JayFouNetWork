package jfnwp.Server;

import jfnwp.Exception.MoveException;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;

public class GameContext {

    private IGame game;
    private int port;
    private String host;
 
    public GameContext(IGame g, String h, int p)
    {
    	port = p;
    	host = h;
    	game = g;
    }
 
    public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}

	public IGame getGame() {
		return game;
	}

	public void applyMove(IMove m) throws MoveException {
        game.applyMove(m);
    }
    
}
