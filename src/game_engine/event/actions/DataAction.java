package game_engine.event.actions;

import java.lang.reflect.Method;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataAction implements Action{
	private Entity myEntity;
	private Class<? extends Component> myComponentClass;
	private Class<?> methodArgType;
	private Method myMethod;
	private String myValue;

	public DataAction(Entity entity, Class<? extends Component> componentClass, Method method, String value){
		myEntity = entity;
		myComponentClass = componentClass;
		myMethod = method;
		myValue = value;
		methodArgType = method.getParameterTypes()[0];
	}

	@Override
	public void execute() {
		Component myComponent = myEntity.getComponent(myComponentClass);
		
		// TODO: arg parsing util
		
		// get arg, get right type, pass
		
		//myMethod.invoke(myComponent, );
	}
}
