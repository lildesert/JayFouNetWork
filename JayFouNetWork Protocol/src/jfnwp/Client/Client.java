package jfnwp.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Implementation.Message;
import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

public class Client {

	public static void main(String[] args) {

		Socket clientSocket = null;
		try {
			clientSocket = new Socket("localhost", RefereeServer.port);
			MessageService m = new MessageService(clientSocket);
			m.Connect("Ju", InetAddress.getLocalHost());
			Message mess = m.ReadMessage();
			System.out.println("FROM SERVER: " + mess.ToString());

			if (mess.getId() == 10) {
				m.GetGames();
			}

			while (true) {
				while (true) {
					mess = m.ReadMessage();
					switch (mess.getId()) {

					case 1:
						break;

					case 2:
						break;

					case 3:
						break;

					case 4:
						break;

					case 5:
						break;

					case 6:
						break;

					case 7:
						break;

					case 8:
						break;

					case 9:
						break;

					case 10:
						break;

					case 11:
						break;

					case 12:
						break;

					case 13:
						break;

					case 14:
						m.SendGames();
						break;

					case 15:
						break;

					case 16:
						break;
						
					default :
						break;
					}
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
