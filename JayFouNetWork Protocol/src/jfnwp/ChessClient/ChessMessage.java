package jfnwp.ChessClient;

import java.net.Socket;

import jfnwp.Interfaces.IMove;
import jfnwp.Services.MessageService;

public class ChessMessage extends MessageService {

	public ChessMessage(Socket sock) {
		super(sock);
	}
	
	public void ClientMove(IMove m)
	{}

}
