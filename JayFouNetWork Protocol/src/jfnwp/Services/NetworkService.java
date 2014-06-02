package jfnwp.Services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import jfnwp.Implementation.Message;

public class NetworkService {

	public static void SendMessage(Socket s, Message m) {
		try {
			byte[] message = SerializationService.toByteArray(m);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			//m.setData("coucou");
			//System.out.println(m.getData());
			
			int msgLength = message.length;
			out.write(message, 0, msgLength);
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
			
			in.readFully(msg);

			m = (Message) SerializationService.toObject(msg);
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
