package jfnwp.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import jfnwp.Implementation.Message;

/**
 * Used to transform String into Byte Array
 * and Byte Array into String
 * @see NetworkService
 * @version 1.0
 */
public class SerializationService {

	public static byte[] toByteArray(Message m) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		DataOutputStream dos = null;
		try {
			bos = new ByteArrayOutputStream();
			dos = new DataOutputStream(bos);
			
			dos.writeInt(m.getId());
			if (m.getData() != null) {
				dos.writeUTF(m.getData());
			}
			
			dos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (dos != null) {
				dos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}

	public static Message toMessage(byte[] bytes) throws IOException,
			ClassNotFoundException {
		Message m = new Message();
		ByteArrayInputStream bis = null;
		DataInputStream dis = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			dis = new DataInputStream(bis);

			m.setId(dis.readInt());
			
			if(bytes.length > 4)
			{
				m.setData(dis.readUTF());
			}
			
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (dis != null) {
				dis.close();
			}
		}
		return m;
	}
}
