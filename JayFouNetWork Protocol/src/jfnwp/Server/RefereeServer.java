package jfnwp.Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jfnwp.Services.MessageService;

public class RefereeServer extends Thread {

	private static final int port = 8888;
	private static final int maxThread = 8;
	private Socket s;

	RefereeServer(Socket sock)
	{
		this.s = sock;
	}

	public static void main(String args[]) throws Exception
	{
		ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
		ServerSocket ssock = new ServerSocket(port);
		System.out.println("Listening");
		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected");
			executorService.execute(new RefereeServer(sock));
		}
	}

	public void run()
	{
		try {
			MessageService m = new MessageService(s);
			
			m.Ok();
			
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
