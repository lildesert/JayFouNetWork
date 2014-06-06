package jfnwp.Implementation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
		return messageMap.get(id);
	}
	
	Map<Integer, String> messageMap = new HashMap<Integer, String>()
	{
		{
			put(1, "CONNECT");
			put(2, "START");
			put(3, "END");
			put(4, "MOVE");
			put(10, "OK");
		}
	};
	
}
