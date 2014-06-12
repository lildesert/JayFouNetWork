package jfnwp.Server;

import jfnwp.Exception.MoveException;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;

public class GameContext {

    IGame game;
 
    public GameContext(IGame g)
    {
    	game = g;
    }
 
    public IGame getGame() {
		return game;
	}

	public void applyMove(IMove m) throws MoveException {
        game.applyMove(m);
    }
    
}
