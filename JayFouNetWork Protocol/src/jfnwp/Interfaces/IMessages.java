package jfnwp.Interfaces;

import jfnwp.Implementation.Message;
import jfnwp.Implementation.Move;

public interface IMessages {

	void Connect(String name);
	void Start(String name);
	void End();
	void Move(Move opponentMove);
	void Result(int code, int nbMove, int gameTime);
	void ClientMove(Move m);
	void Surrender();
	void GetAdresses();
	void SendAdresses();
	void Ok();
	void Nok();
	void Wait();
	void Imp();
	void Error(String errMsg);
	Message ReadMessage();
}
