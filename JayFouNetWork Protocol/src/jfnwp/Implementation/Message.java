package jfnwp.Implementation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent a message 
 * 	--> ID
 *  --> Content
 * @see RFC
 * @version 1.0
 */
public class Message implements Serializable {

	private int id;
	private String data;
	
	public Message()
	{ }
	
	public Message(int idM)
	{
		id = idM;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String ToString()
	{
		return messageMap.get(id) +"\n" +"Data : "+data;
	}
	
	/**
	 * IP and type of message
	 * @see RFC
	 * @version 1.0
	 */
	Map<Integer, String> messageMap = new HashMap<Integer, String>()
	{
		{
			put(1, "CONNECT");
			put(2, "START");
			put(3, "END");
			put(4, "MOVE");
			put(5, "RESULT");
			put(6, "CLIENTMOVE");
			put(7, "SURRENDER");
			put(8, "GETADRESSES");
			put(9, "SENDADRESSES");
			put(10, "OK");
			put(11, "NOK");
			put(12, "WAIT");
			put(13, "IMP");
			put(14, "ERROR");
		}
	};
	
}
