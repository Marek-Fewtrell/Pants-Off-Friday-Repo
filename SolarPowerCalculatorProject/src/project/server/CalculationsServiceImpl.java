package project.server;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import project.client.CalculationsService;
import project.shared.CalcException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CalculationsServiceImpl extends RemoteServiceServlet implements CalculationsService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String decFormat = "#.##";
	
	@Override
	public ArrayList<ArrayList<String>> CalculationsServer(HashMap<String, String> asd) throws CalcException {
		/*if (asd.isEmpty() || asd.get(0) == null || asd.get(0).isEmpty()) {
			throw new CalcException("Stuff is null");
		}*/
		
//		Calculations calcs = new Calculations(asd.get(0), Integer.parseInt(asd.get(1)), Integer.parseInt(asd.get(2)), asd.get(3), asd.get(4), Double.parseDouble(asd.get(5)), 
//				Integer.parseInt(asd.get(6)), Integer.parseInt(asd.get(7)), Integer.parseInt(asd.get(8)));
		
		
		String panelNumber = asd.get("panelSelect");
		int numberPanels = Integer.parseInt(asd.get("panelNumber"));
		String suburb = asd.get("suburb");
		String inverterNumber = asd.get("inverterSelect");
		String energyCompany = asd.get("energyProvider");
		double dailyUsage = Double.parseDouble(asd.get("dayTimeUsage"));
		int tilt = Integer.parseInt(asd.get("tiltAngle"));
		int orientation= Integer.parseInt(asd.get("panelDirection"));
		double initInstallCost = Double.parseDouble(asd.get("initialInstallCost"));
		double interestRate = Double.parseDouble(asd.get("interestRate"));
		
		
		Calculations calcs = new Calculations(panelNumber, numberPanels, suburb, inverterNumber, energyCompany, 
				dailyUsage, tilt, orientation);
		
		
		/*Calculations calcs = new Calculations(asd.get(0), Integer.parseInt(asd.get(1)), asd.get(2), asd.get(3), asd.get(4), 
				Double.parseDouble(asd.get(5)), Integer.parseInt(asd.get(6)), Integer.parseInt((asd.get(7))));*/

		DecimalFormat decForm = new DecimalFormat(decFormat);

		ArrayList<String> array1 = new ArrayList<String>();//Year
		ArrayList<String> array2 = new ArrayList<String>();//Daily Gen
		ArrayList<String> array3 = new ArrayList<String>();//Yearly Gen
		ArrayList<String> array4 = new ArrayList<String>();//Yearly Savings
		ArrayList<String> array5 = new ArrayList<String>();//Invest Return
		ArrayList<String> array6 = new ArrayList<String>();//Break even
		ArrayList<ArrayList<String>> arrayOut = new ArrayList<ArrayList<String>>();
		
		double[] savings = new double[20];
		savings = calcs.getCumulativeSavings(20);
		double[] investment = new double [20];
		investment[0] = initInstallCost *(1 + interestRate / 100);
		for(int i=1; i<investment.length; i++){
			investment[i] = investment[i - 1] * (1 + interestRate / 100);
		}
		
		//Year
		for (int i = 1; i < savings.length + 1; i++) {
			array1.add(Integer.toString(i));
		}
		//Daily Generation
		for(int i=0; i<savings.length; i++){
			array2.add(decForm.format(calcs.getPowerGenerated(i)));
		}
		//Yearly Generation
		for(int i=0; i<savings.length; i++){
			array3.add(decForm.format(calcs.getPowerGenerated(i)*365.25));
		}
		//Yearly Savings
		for (int i=0; i<savings.length; i++){
			array4.add(decForm.format(savings[i]));
		}
		//Investment Return
		for(int i=0; i<investment.length; i++){
			array5.add(decForm.format(investment[i]));
		}
		
		
		array6.add(decForm.format((calcs.getBreakEven(initInstallCost, interestRate))));
		
		arrayOut.add(array1);
		arrayOut.add(array2);
		arrayOut.add(array3);
		arrayOut.add(array4);
		arrayOut.add(array5);
		arrayOut.add(array6);
		
		return arrayOut;
	}
	
}
