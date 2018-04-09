package observables;

import java.util.ArrayList;
import java.util.List;

import observables.Listener;

public interface Subject {

	public  List<Listener> myListeners = new ArrayList<Listener>();

	public void addListener(Listener l);
	
	public void removeListener(Listener l);
	
	public void notifyListeners(String key);
	
}
