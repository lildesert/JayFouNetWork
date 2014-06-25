package jfnwp.Interfaces;

import jfnwp.Implementation.Message;

public interface IMessages {

	void Connect(String name, String ip);
	void Start(String name);
	void End();
	void Move(IMove opponentMove);
	void Result(String result);
	void ClientMove(IMove m);
	void Surrender();
	void GetAdresses();
	void SendAdresses();
	void Ok();
	void Nok();
	void Wait(String info);
	void Imp();
	void Error(String errMsg);
	void GetGames();
	void SendGames();
	Message ReadMessage();
}
