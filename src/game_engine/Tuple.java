package game_engine;

public class Tuple<T1, T2> {
	
	private T1 myFirst;
	private T2 mySecond;
	
	public Tuple(T1 x, T2 y) { 
		myFirst = x; 
		mySecond = y; 
	}
	
	public T1 getFirst() {
		return myFirst;
	}
	
	public T2 getSecond() {
		return mySecond;
	}
}
