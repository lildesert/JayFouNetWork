package jfnwp.Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args)  {
		
		String sentence;            
        String modifiedSentence;
 
        Socket clientSocket = null;
		try 
		{
			clientSocket = new Socket("localhost", 8888);
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());        
	        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	 
	        sentence = inFromUser.readLine();
	        outToServer.writeBytes(sentence + '\n');
	        modifiedSentence = inFromServer.readLine();
	        System.out.println("FROM SERVER: " + modifiedSentence);
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
