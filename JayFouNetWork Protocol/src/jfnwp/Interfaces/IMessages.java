package jfnwp.Interfaces;

import jfnwp.Implementation.Message;

public interface IMessages {
	
	//Message Id = 1
	void Connect(String name, String ip);
	
	//Message Id = 2
	void Start(String name);
	
	//Message Id = 3
	void End();
	
	//Message Id = 4
	void Move();
	
	//Message Id = 5
	void Result(String result);
	
	//Message Id = 6
	void ClientMove(String mv);
	
	//Message Id = 8
	void GetAdress();
	
	//Message Id = 9
	void SendAdress(String data);
	
	//Message Id = 10
	void Ok();
	
	//Message Id = 12
	void Wait(String info);
	
	//Message Id = 14
	void Error(String errMsg);
	
	//Message Id = 15
	void GetGames();
	
	//Message Id = 16
	void SendGames();
	
	//Message Id = 17
	void Info(String inf);
	
	Message ReadMessage();
}
