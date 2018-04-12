package game_engine.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import game_engine.Component;
import game_engine.Vector;

public class MovementRequestsComponent implements Component, Iterable{
	// tmeporarly a list of lists
	private List<Vector> ds;
	
	public MovementRequestsComponent() {
		clear();
	}
	
	public void addMoveVector(Vector v) {
		ds.add(v);
	}
	
	public void clear() {
		ds = new CopyOnWriteArrayList<Vector>();
	}

	@Override
	public Iterator iterator() {
		return ds.iterator();
	}
}
