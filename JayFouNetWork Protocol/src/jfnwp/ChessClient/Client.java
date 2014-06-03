package jfnwp.ChessClient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

public class Client {


	public static void main(String[] args)  {
 
        Socket clientSocket = null;
		try 
		{
			clientSocket = new Socket("localhost", RefereeServer.port);
			MessageService m = new MessageService(clientSocket);
			m.Ok();
	        //System.out.println("FROM SERVER: " + m.ReadMessage());
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
