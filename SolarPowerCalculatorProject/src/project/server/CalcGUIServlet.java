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
		
		String panelNumber = "REC215PE";//request.getParameter("panelNum").toString();
    	Integer numberPanels = 8;//Integer.parseInt(request.getParameter("numPanel").toString());
    	String postcode = "4053";//request.getParameter("postcode").toString();
    	String inverterNumber = "1.5kTL";//request.getParameter("invNum").toString();
    	String energyCompany = "Click Energy";//request.getParameter("energyComp").toString();
    	Double dailyUsage = 12.3;//Double.parseDouble(request.getParameter("dailyUsage").toString());
    	Integer tilt = 12;//Integer.parseInt(request.getParameter("tilt").toString());
    	Integer orientation = 12;//Integer.parseInt(request.getParameter("orientation").toString());
    	Integer initInstallCost = 1000;//Integer.parseInt(request.getParameter("initInstalCost").toString());
    	Integer interestRate = 1;
		
    	ArrayList<ArrayList<String>> arrayOut = null;
    	
		//.........
		try {
			Calculations calcs = new Calculations(panelNumber, numberPanels, postcode, inverterNumber, energyCompany, 
							dailyUsage, tilt, orientation);
			
			DecimalFormat decForm = new DecimalFormat(decFormat);

			ArrayList<String> yearlyArray = new ArrayList<String>();//Year
			ArrayList<String> dailyGenResultArray = new ArrayList<String>();//Daily Gen
			ArrayList<String> yearlyGenResultArray = new ArrayList<String>();//Yearly Gen
			ArrayList<String> yearlySavingResultArray = new ArrayList<String>();//Yearly Savings
			ArrayList<String> investReturnResultArray = new ArrayList<String>();//Invest Return
			ArrayList<String> breakEvenArray = new ArrayList<String>();//Break even
			arrayOut = new ArrayList<ArrayList<String>>();
			
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
			
		} catch (CalcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/plain");
		/*response.getWriter().println("Hello, world");
		response.getWriter().println("Marek is awesome");*/
		response.getWriter().println(arrayOut.toString());
		
		//request.setAttribute("solarPanel", "Marek is awesome");

/*InputStream in = request.getInputStream();
ObjectInputStream input = new ObjectInputStream(in);
System.out.println(input);
String echo = null;

		echo = "Awesome";



OutputStream out = response.getOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(out);
oos.writeObject(echo);
oos.flush();
oos.close();*/
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        
    }
	
}
