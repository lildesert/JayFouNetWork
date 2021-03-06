package jfnwp.Services;

import java.net.Socket;

import jfnwp.Interfaces.IMessages;
import jfnwp.Interfaces.IMove;
import jfnwp.Implementation.EnumGame;
import jfnwp.Implementation.Message;

/**
 * Class used to encapsulate the messages to be send
 * and create a new message object for the content that is received
 * @version 1.0
 */
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
		Message m = new Message(3);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Move() {
		Message m = new Message(4);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Result(String result) {
		Message m = new Message(5);
		m.setData(result);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void ClientMove(String mv) {
		Message m = new Message(6);
		m.setData(mv);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void GetAdress() {
		Message m = new Message(8);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void SendAdress(String data) {
		Message m = new Message(9);
		m.setData(data);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Ok() {
		Message m = new Message(10);
		NetworkService.SendMessage(s, m);
	}

	@Override
	public void Wait(String info) {
		Message m = new Message(12);
		m.setData(info);
		NetworkService.SendMessage(s, m);
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

	@Override
	public void Info(String inf) {
		Message m = new Message(17);
		m.setData(inf);
		NetworkService.SendMessage(s, m);
	}
}
