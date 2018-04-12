package gameData;

import java.io.Serializable;
import java.util.HashMap;

public class Abs extends Super{
	private int a;
	private double b;
	private Chest f;

	public Abs (int t, double y) {
		this.a = t;
		this.b = y;
		this.f = new Chest(t, (int)y);
	}

	@Override
	public void update() {
		this.a++;
		this.b--;
		f.change();
	}
	public int getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public int getChestC() {
		return f.c;
	}
	public int getChestD() {
		return f.d;
	}
	public HashMap<Integer, Integer> get() {
		return f.map;
	}
}
