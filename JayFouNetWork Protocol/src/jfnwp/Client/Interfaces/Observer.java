package jfnwp.Client.Interfaces;

import jfnwp.Implementation.ObservableData;

// Pattern Observable
public interface Observer {
	void update(ObservableData d);
}
