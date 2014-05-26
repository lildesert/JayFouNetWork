package jfnwp.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Services.MessageService;

public class Client {

	public static void main(String[] args) {
 
        Socket clientSocket;
		try 
		{
			clientSocket = new Socket("localhost", 8888);
			MessageService m = new MessageService(clientSocket);
			m.Ok();
	        System.out.println("FROM SERVER: " + m.ReadMessage());
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			//clientSocket.close();	
		}
	}

}
