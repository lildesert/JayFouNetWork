package jfnwp.Chat;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import jfnwp.Client.Interfaces.Observable;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;

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

    public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void delObserver() {
		this.listObserver = new ArrayList<Observer>();
	}

	public void updateObserver() {
		for (Observer obs : this.listObserver) {
			obs.update(data);
		}
	}

}
