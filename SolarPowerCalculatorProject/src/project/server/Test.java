package project.server;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculations test = new Calculations("a", 30, "a", "a", "l");
		System.out.println(test.getPowerGenerated(1));
		System.out.println(test.getDailySavings(1));
		System.out.println(test.getCumulativeSavings(5));
	}

}