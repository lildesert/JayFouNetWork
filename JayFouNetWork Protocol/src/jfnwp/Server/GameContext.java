package jfnwp.Server;

import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;

public class GameContext {

    IGame game;
 
    public GameContext(IGame g)
    {
    	game = g;
    }
 
    public void applyMove(IMove m) {
        game.applyMove(m);
    }
    
}
