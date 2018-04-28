package game_engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Component<T> {
	private T myValue;

	public Component(T val) {
		myValue = val;
	}

	public void setValue(T val) {
		myValue = val;
	}
	
	public T getValue() {
		return myValue;
	}
	
	public Component<?> copy(){
		Class<? extends Component<?>> c = (Class<? extends Component<?>>) this.getClass();
		Constructor ctor;
		try {
			if(myValue instanceof NullType) {
				ctor = c.getConstructor();
				return (Component<?>) ctor.newInstance();
			}
			else {
				ctor = c.getConstructor(String.class);
				return (Component<?>) ctor.newInstance(myValue.toString());
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException |NoSuchMethodException | SecurityException e) {
			throw new ComponentNotFoundException("Error in deep copying component: " + c.getName());
		}
	}
}
