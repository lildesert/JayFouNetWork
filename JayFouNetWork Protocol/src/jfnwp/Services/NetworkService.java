package jfnwp.Services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Implementation.Message;

public class NetworkService {
	
	private static Logger logger = LogManager.getLogger(NetworkService.class.getName());

	public static void SendMessage(Socket s, Message m) {
		try {
			byte[] message = SerializationService.toByteArray(m);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());			
			int msgLength = message.length;
			out.writeInt(msgLength);
			out.write(message);
			logger.info("Message de taille "+msgLength + " envoyé");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Message receiveMessage(Socket s) {
		Message m = new Message();
		DataInputStream in;
		try {
			in = new DataInputStream(s.getInputStream());
			
			int msgLen = in.readInt();
			byte[] msg = new byte[msgLen];
			
			in.read(msg);
			m = SerializationService.toMessage(msg);
			logger.info("Message de taille " +msgLen +" reçu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}

}
