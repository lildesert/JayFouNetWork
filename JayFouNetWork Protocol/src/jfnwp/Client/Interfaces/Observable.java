package jfnwp.Client.Interfaces;

public interface Observable {
	void addObserver(Observer obs);
	void updateObserver();
	void delObserver();
}
