
package jfnwp.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Implementation.Message;
import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

public class Client {

	public static void main(String[] args)  {
 
        Socket clientSocket = null;
		try 
		{
			clientSocket = new Socket("localhost", RefereeServer.port);
			MessageService m = new MessageService(clientSocket);
			m.Connect("Ju", InetAddress.getLocalHost());
			//m.Ok();
			Message mess = m.ReadMessage();
	        System.out.println("FROM SERVER: " + mess.ToString());
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
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

}
