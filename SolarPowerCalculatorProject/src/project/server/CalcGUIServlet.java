package project.server;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.shared.CalcException;


public class CalcGUIServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String decFormat = "#.##";
	
	@Override
	public void doPost(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {
		//request.getAttribute("input1");
		
//		String panelNumber = "REC215PE";//request.getParameter("panelNum").toString();
//    	Integer numberPanels = 8;//Integer.parseInt(request.getParameter("numPanel").toString());
//    	String postcode = "4053";//request.getParameter("postcode").toString();
//    	String inverterNumber = "1.5kTL";//request.getParameter("invNum").toString();
//    	String energyCompany = "Click Energy";//request.getParameter("energyComp").toString();
//    	Double dailyUsage = 12.3;//Double.parseDouble(request.getParameter("dailyUsage").toString());
//    	Integer tilt = 12;//Integer.parseInt(request.getParameter("tilt").toString());
//    	Integer orientation = 12;//Integer.parseInt(request.getParameter("orientation").toString());
//    	Integer initInstallCost = 1000;//Integer.parseInt(request.getParameter("initInstalCost").toString());
//    	Integer interestRate = 1;

		String panelNumber = request.getParameter("panelNum").toString();
    	Integer numberPanels = Integer.parseInt(request.getParameter("numPanel").toString());
    	String postcode = request.getParameter("postcode").toString();
    	String inverterNumber = request.getParameter("invNum").toString();
    	String energyCompany = request.getParameter("energyComp").toString();
    	Double dailyUsage = Double.parseDouble(request.getParameter("dailyUsage").toString());
    	Integer tilt = Integer.parseInt(request.getParameter("tilt").toString());
    	Integer orientation = Integer.parseInt(request.getParameter("orientation").toString());
    	Integer initInstallCost = Integer.parseInt(request.getParameter("initInstalCost").toString());
    	Integer interestRate = 1;
		
    	String yearlyArray = new String("yearlyArray");//Year
		String dailyGenResultArray = new String("dailyGenResultArray");//Daily Gen
		String yearlyGenResultArray = new String("yearlyGenResultArray");//Yearly Gen
		String yearlySavingResultArray = new String("yearlySavingResultArray");//Yearly Savings
		String investReturnResultArray = new String("investReturnResultArray");//Invest Return
		String breakEvenArray = new String("breakEvenArray");//Break even
		
		//.........
		try {
			Calculations calcs = new Calculations(panelNumber, numberPanels, postcode, inverterNumber, energyCompany, 
							dailyUsage, tilt, orientation);
			
			DecimalFormat decForm = new DecimalFormat(decFormat);

			
			
			double[] savings = new double[20];
			savings = calcs.getCumulativeSavings(20);
			double[] investment = new double [20];
			investment[0] = initInstallCost *(1 + interestRate / 100);
			for(int i=1; i<investment.length; i++){
				investment[i] = investment[i - 1] * (1 + interestRate / 100);
			}
			
			//Year
			for (int i = 1; i < savings.length + 1; i++) {
				yearlyArray += (Integer.toString(i));
			}
			//Daily Generation
			for(int i=0; i<savings.length; i++){
				dailyGenResultArray += (decForm.format(calcs.getPowerGenerated(i)));
			}
			//Yearly Generation
			for(int i=0; i<savings.length; i++){
				yearlyGenResultArray +=(decForm.format(calcs.getPowerGenerated(i)*365.25));
			}
			//Yearly Savings
			for (int i=0; i<savings.length; i++){
				yearlySavingResultArray += (decForm.format(savings[i]));
			}
			//Investment Return
			for(int i=0; i<investment.length; i++){
				investReturnResultArray += (decForm.format(investment[i]));
			}
			
			
			breakEvenArray += (decForm.format((calcs.getBreakEven(initInstallCost, interestRate))));
			
		} catch (CalcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.setContentType("text/plain");
		response.getWriter().println(yearlyArray);
		response.getWriter().println(dailyGenResultArray);
		response.getWriter().println(yearlyGenResultArray);
		response.getWriter().println(yearlySavingResultArray);
		response.getWriter().println(investReturnResultArray);
		response.getWriter().println(breakEvenArray);
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        
    }
	
}
