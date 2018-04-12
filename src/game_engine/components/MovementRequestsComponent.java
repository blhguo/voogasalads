<<<<<<< HEAD:src/game_engine/components/MovementRequestComponent.java
//package game_engine.components;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import game_engine.Component;
//import game_engine.Vector;
//
//public class MovementRequestComponent implements Component{
//	// tmeporarly a list of lists
//	private List<Vector> ds;
//	
//	public MovementRequestComponent() {
//		clear();
//	}
//	
//	public void addMoveVector(Vector v) {
//		ds.add(v);
//	}
//	
//	public void clear() {
//		ds = new CopyOnWriteArrayList<Vector>();
//	}
//}
=======
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

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
>>>>>>> 08fdd98b9b284bd08b899019799e5816ea6a5dce:src/game_engine/components/MovementRequestsComponent.java
