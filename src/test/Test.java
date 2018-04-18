package test;

import com.udojava.evalex.Expression;

public class Test {
	public static void main(String[] args) {
		System.out.println(new Expression("FalSe + 1 == true").eval());
	}
}
