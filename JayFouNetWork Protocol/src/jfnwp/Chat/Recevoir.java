package jfnwp.Chat;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import jfnwp.Client.Interfaces.Observable;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;

/**
 * Is responsible for receiving messages and 
 * alert the GUI
 */
public class Recevoir implements Runnable, Observable {
	
	private String hostname;
	private int port;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	ObservableData data = new ObservableData();
	
	public Recevoir(String host, int p)
	{
		hostname = host;
		port = p;
	}
	
	/**
	 * Begin receiving messages
     * @version 1.0
     */
    public void run() {
        try
        {
            InetAddress group = InetAddress.getByName(hostname);
            MulticastSocket s = new MulticastSocket(port);
            s.joinGroup(group);
            while (true) {
                byte[] buf = new byte[1000];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                data.setInfo(new String(recv.getData()).trim());
                updateObserver();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Add an observer
     * @version 1.0
     */
    public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
    
    /**
     * Remove an observer
     * @version 1.0
     */
	public void delObserver() {
		this.listObserver = new ArrayList<Observer>();
	}
	
	/**
	 * Prevent observers
     * @version 1.0
     */
	public void updateObserver() {
		for (Observer obs : this.listObserver) {
			obs.update(data);
		}
	}

}
