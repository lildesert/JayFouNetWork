package jfnwp.Services;

import java.net.Socket;

import jfnwp.Interfaces.IMessages;
import jfnwp.Interfaces.IMove;
import jfnwp.Implementation.EnumGame;
import jfnwp.Implementation.Message;

public class MessageService implements IMessages {

	private Socket s;

	public MessageService(Socket sock) {
		s = sock;
	}

	@Override
	public void Connect(String name, String ip) {
		Message m = new Message(1);
		m.setData(name + ";" + ip);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Start(String name) {
		Message m = new Message(2);
		m.setData(name);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void End() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move(IMove opponentMove) {
		Message m = new Message(4);
		if (opponentMove != null) {
			m.setData(opponentMove.toString());
		}
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Result(String result) {
		Message m = new Message(5);
		m.setData(result);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void ClientMove(IMove mv) {
		Message m = new Message(6);
		m.setData(mv.getSerializedData());
		NetworkService.SendMessage(s, m);
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
	public void Wait(String info) {
		Message m = new Message(12);
		m.setData(info);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Imp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Error(String errMsg) {
		Message m = new Message(14);
		m.setData(errMsg);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public Message ReadMessage() {
		Message m = NetworkService.receiveMessage(s);
		return m;
	}

	@Override
	public void GetGames() {
		Message m = new Message(15);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void SendGames() {
		Message m = new Message(16);
		String d = "";
		for (EnumGame eg : EnumGame.values()) {
			d += eg.toString() + ";";
		}
		m.setData(d);
		NetworkService.SendMessage(s, m);
	}
}
