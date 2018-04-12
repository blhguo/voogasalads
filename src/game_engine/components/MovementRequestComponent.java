package game_engine.components;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import game_engine.Component;
import game_engine.Vector;

public class MovementRequestComponent implements Component{
	// tmeporarly a list of lists
	private List<Vector> ds;
	
	public MovementRequestComponent() {
		clear();
	}
	
	public void addMoveVector(Vector v) {
		ds.add(v);
	}
	
	public void clear() {
		ds = new CopyOnWriteArrayList<Vector>();
	}
}
