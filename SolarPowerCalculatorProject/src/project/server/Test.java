package project.server;

import project.shared.CalcException;

public class Test {

	/**
	 * @param args
	 * @throws CalcException 
	 */
	public static void main(String[] args) throws CalcException {
		// TODO Auto-generated method stub
		Calculations test = new Calculations("a", 30, "Brisbane", "a", "l", 5.0, 25, 0);
		System.out.println(test.getPowerGenerated(1));
		System.out.println(test.getDailySavings(1));
		System.out.println(test.getCumulativeSavings(2));
	}

}
