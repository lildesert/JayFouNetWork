package jfnwp.Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jfnwp.Services.MessageService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RefereeServer extends Thread {

	private static final int port = 9890;
	private static final int maxThread = 8;
	private Socket s;
	private static Logger logger = LogManager.getLogger(RefereeServer.class.getName());

	RefereeServer(Socket sock)
	{
		this.s = sock;
	}

	public static void main(String args[]) throws Exception
	{
		ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
		ServerSocket ssock = new ServerSocket(port);
		logger.info("Server start");
		logger.info("test");
		while (true) {
			Socket sock = ssock.accept();
			logger.info("Connection of a new player");
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
