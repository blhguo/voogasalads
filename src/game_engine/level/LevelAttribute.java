package game_engine.level;

public abstract class LevelAttribute<T> {
	private T myAttribute;
	public LevelAttribute(T arg){
		myAttribute = arg;
	}
	public T getAttribute(){
		return myAttribute;
	}
	public void setAttribute(T attr){
		myAttribute = attr;
	}
}
