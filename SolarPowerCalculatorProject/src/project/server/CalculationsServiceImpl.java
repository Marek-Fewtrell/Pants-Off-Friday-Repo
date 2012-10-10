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
		
		String panelNumber = asd.get("panelSelect");
		int numberPanels = Integer.parseInt(asd.get("panelNumber"));
		String postcode = asd.get("postcode");
		String inverterNumber = asd.get("inverterSelect");
		String energyCompany = asd.get("energyProvider");
		double dailyUsage = Double.parseDouble(asd.get("dayTimeUsage"));
		int tilt = Integer.parseInt(asd.get("tiltAngle"));
		int orientation= Integer.parseInt(asd.get("panelDirection"));
		double initInstallCost = Double.parseDouble(asd.get("initialInstallCost"));
		double interestRate = Double.parseDouble(asd.get("interestRate"));
		
		
		Calculations calcs = new Calculations(panelNumber, numberPanels, postcode, inverterNumber, energyCompany, 
				dailyUsage, tilt, orientation);
		
		DecimalFormat decForm = new DecimalFormat(decFormat);

		ArrayList<String> yearlyArray = new ArrayList<String>();//Year
		ArrayList<String> dailyGenResultArray = new ArrayList<String>();//Daily Gen
		ArrayList<String> yearlyGenResultArray = new ArrayList<String>();//Yearly Gen
		ArrayList<String> yearlySavingResultArray = new ArrayList<String>();//Yearly Savings
		ArrayList<String> investReturnResultArray = new ArrayList<String>();//Invest Return
		ArrayList<String> breakEvenArray = new ArrayList<String>();//Break even
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
			yearlyArray.add(Integer.toString(i));
		}
		//Daily Generation
		for(int i=0; i<savings.length; i++){
			dailyGenResultArray.add(decForm.format(calcs.getPowerGenerated(i)));
		}
		//Yearly Generation
		for(int i=0; i<savings.length; i++){
			yearlyGenResultArray.add(decForm.format(calcs.getPowerGenerated(i)*365.25));
		}
		//Yearly Savings
		for (int i=0; i<savings.length; i++){
			yearlySavingResultArray.add(decForm.format(savings[i]));
		}
		//Investment Return
		for(int i=0; i<investment.length; i++){
			investReturnResultArray.add(decForm.format(investment[i]));
		}
		
		
		breakEvenArray.add(decForm.format((calcs.getBreakEven(initInstallCost, interestRate))));
		
		arrayOut.add(yearlyArray);
		arrayOut.add(dailyGenResultArray);
		arrayOut.add(yearlyGenResultArray);
		arrayOut.add(yearlySavingResultArray);
		arrayOut.add(investReturnResultArray);
		arrayOut.add(breakEvenArray);
		
		return arrayOut;
	}
	
}
