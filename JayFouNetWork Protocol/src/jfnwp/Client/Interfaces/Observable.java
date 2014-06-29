package jfnwp.Client.Interfaces;

// Pattern Observable 
public interface Observable {
	void addObserver(Observer obs);
	void updateObserver();
	void delObserver();
}
