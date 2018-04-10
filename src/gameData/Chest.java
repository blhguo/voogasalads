package gameData;

import java.io.Serializable;
import java.util.HashMap;

public class Chest implements Serializable{
	public int c;
	public int d;
	public HashMap<Integer, Integer> map;
	
	public Chest(int e, int f) {
		this.c = e;
		this.d = f;
		map = new HashMap<Integer, Integer>();
	}
	
	public void change() {
		map.put(9, 9);
		c = 5;
		d = 10;
	}
}
