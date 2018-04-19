package test;

import com.udojava.evalex.Expression;

public class Test {
	public static void main(String[] args) {
		System.out.println(new Expression(xVel.getValue()) + -1 * xAccel * elapsedTime).eval());
	}
}
