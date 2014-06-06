package jfnwp.Services;

import java.net.Socket;

import jfnwp.Interfaces.IMessages;
import jfnwp.Interfaces.IMove;
import jfnwp.Implementation.Message;

public class MessageService implements IMessages {

	private Socket s;
	
	public MessageService(Socket sock)
	{
		s = sock;
	}
	
	@Override
	public void Connect(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Start(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void End() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Move(IMove opponentMove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Result(int code, int nbMove, int gameTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClientMove(IMove m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Surrender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetAdresses() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SendAdresses() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Ok() {
		Message m = new Message(10);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Nok() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wait() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Imp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Error(String errMsg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ReadMessage() {
		Message m = NetworkService.receiveMessage(s);
		return m.ToString();
		//return m;
	}	
}
