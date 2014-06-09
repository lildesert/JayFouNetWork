package jfnwp.Interfaces;

import java.net.InetAddress;

import jfnwp.Implementation.Message;

public interface IMessages {

	void Connect(String name, InetAddress c);
	void Start(String name);
	void End();
	void Move(IMove opponentMove);
	void Result(int code, int nbMove, int gameTime);
	void ClientMove(IMove m);
	void Surrender();
	void GetAdresses();
	void SendAdresses();
	void Ok();
	void Nok();
	void Wait();
	void Imp();
	void Error(String errMsg);
	void GetGames();
	void SendGames();
	Message ReadMessage();
}
