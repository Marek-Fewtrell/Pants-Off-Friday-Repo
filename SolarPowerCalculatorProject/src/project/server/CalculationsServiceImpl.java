package project.server;

import java.text.DecimalFormat;
import java.util.ArrayList;

import project.client.CalcException;
import project.client.CalculationsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CalculationsServiceImpl extends RemoteServiceServlet implements CalculationsService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String decFormat = "#.##";
	
	@Override
	public ArrayList<String> CalculationsServer(ArrayList<String> asd) throws CalcException {
		
		if (asd.isEmpty() || asd.get(0) == null || asd.get(0).isEmpty()) {
			throw new CalcException("Stuff is null");
		}
		
//		Calculations calcs = new Calculations(asd.get(0), Integer.parseInt(asd.get(1)), Integer.parseInt(asd.get(2)), asd.get(3), asd.get(4), Double.parseDouble(asd.get(5)), 
//				Integer.parseInt(asd.get(6)), Integer.parseInt(asd.get(7)), Integer.parseInt(asd.get(8)));
		
		Calculations calcs = new Calculations(asd.get(0), Integer.parseInt(asd.get(1)), asd.get(2), asd.get(3), asd.get(4), 
				Double.parseDouble(asd.get(5)), Integer.parseInt(asd.get(6)), Integer.parseInt((asd.get(7))));

		DecimalFormat decForm = new DecimalFormat(decFormat);
		
		/*
		FlexTable generatedTable = new FlexTable();
		
		//Create table row names for data
		generatedTable.setText(0, 0, "Year");
		generatedTable.setText(1, 0, "Daily Generation");
		generatedTable.setText(2, 0, "Yearly Generation");
		generatedTable.setText(3, 0, "Yearly Savings");
		generatedTable.setText(4, 0, "Investment Return");
*/
		
		ArrayList<String> fgh = new ArrayList<String>();
		Double installationCost = Double.parseDouble(asd.get(9));
		Double interestRate = Double.parseDouble(asd.get(10));
		
		double[] savings = new double[20];
		savings = calcs.getCumulativeSavings(20);
		double[] investment = new double [20];
		investment[0] = installationCost;
		for(int i=1; i<investment.length; i++){
			investment[i] = investment[i-1]*(1+interestRate/100);
		}
		
		//Year
		for (int i = 1; i < savings.length + 1; i++) {
			//generatedTable.setText(0, i, i);
			//generatedTable.setText(0, i, "Year");
			fgh.add(Integer.toString(i));
		}
		//Daily Generation
		for(int i=0; i<savings.length; i++){
			String temp = decForm.format(calcs.getPowerGenerated(i));
			fgh.add(temp);
		}
		//Yearly Generation
		for(int i=0; i<savings.length; i++){
			String temp = decForm.format(calcs.getPowerGenerated(i)*365.25);
			fgh.add(temp);
		}
		//Yearly Savings
		for (int i=0; i<savings.length; i++){
			fgh.add(decForm.format(savings[i]));
		}
		//Investment Return
		for(int i=0; i<investment.length; i++){
			fgh.add(decForm.format(investment[i]));
		}
		
		
		return fgh;
	}


	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	/*private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}*/
	
}
