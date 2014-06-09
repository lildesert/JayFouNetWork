package jfnwp.RpslsImplementation;

import java.net.Socket;

import jfnwp.Interfaces.IMove;
import jfnwp.Services.MessageService;

public class MessageRpsls extends MessageService {

	public MessageRpsls(Socket sock) {
		super(sock);
	}
	
	public void ClientMove(IMove m)
	{}
}
