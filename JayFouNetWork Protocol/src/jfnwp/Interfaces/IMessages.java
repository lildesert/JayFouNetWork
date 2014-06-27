package jfnwp.Interfaces;

import jfnwp.Implementation.Message;

public interface IMessages {

	void Connect(String name, String ip);
	void Start(String name);
	void End();
	void Move();
	void Result(String result);
	void ClientMove(String mv);
	void Surrender();
	void GetAdress();
	void SendAdress(String data);
	void Ok();
	void Nok();
	void Wait(String info);
	void Imp();
	void Error(String errMsg);
	void GetGames();
	void SendGames();
	void Info(String inf);
	Message ReadMessage();
}
