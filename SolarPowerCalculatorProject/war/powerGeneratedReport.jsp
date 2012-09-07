<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "project.server.Calculations"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>INB372 - Agile Software Development Solar Power
	Calculator Project</title>
</head>

<body>
	<br />!----------Begin Body Area----------!
	<br />
	<%
	String solarPanel = (String)session.getAttribute("solarPanel");
	int numPanels = Integer.parseInt((String)session.getAttribute("numPanels"));
	String suburb = (String)session.getAttribute("suburb");
	String inverter = (String)session.getAttribute("inverter");
	String energyProvider = (String)session.getAttribute("energyProvider");
	Double daytimeUsage = Double.parseDouble((String)session.getAttribute("daytimeUsage"));
	//Double efficiencyLoss = Double.parseDouble((String)session.getAttribute("efficiencyLoss"));
	Double installationCost = Double.parseDouble((String)session.getAttribute("installationCost"));
	Double interestRate = Double.parseDouble((String)session.getAttribute("interestRate"));
	int latitude = Integer.parseInt((String)session.getAttribute("latitude"));
	int tilt = Integer.parseInt((String)session.getAttribute("tiltAngle"));
	int orientation = Integer.parseInt((String)session.getAttribute("orientation"));
	out.print(session.getAttribute("solarPanel"));
	out.print(session.getAttribute("energyProvider"));
	Calculations calcs = new Calculations(solarPanel, numPanels, suburb, inverter, energyProvider, daytimeUsage, latitude, tilt, orientation);	
	double[] savings = new double[20];
	savings = calcs.getCumulativeSavings(20);
	double[] investment = new double [20];
	investment[0] = installationCost;
	for(int i=1; i<investment.length; i++){
		investment[i] = investment[i-1]*(1+interestRate/100);
	}
	out.print("<table><tr><td>Year</td>");
	for(int i=1; i<savings.length + 1; i++){
		out.print("<td>" + i + "</td>");
	}
	
	out.print("</tr><tr><td>Daily Generation</td>");
	for(int i=0; i<savings.length; i++){
		out.print("<td>"+ calcs.getPowerGenerated(i) +"</td>");
	}
	out.print("</tr><tr><td>Yearly Generation</td>");
	for(int i=0; i<savings.length; i++){
		out.print("<td>"+ calcs.getPowerGenerated(i)*365.25 +"</td>");
	}
	out.print("</tr><tr><td>Yearly Savings</td>");
	for (int i=0; i<savings.length; i++){
		out.print("<td>" + savings[i] + "</td>");
	}
	out.print("</tr><tr><td>Investment Return</td>");
	for(int i=0; i<investment.length; i++){
		out.print("<td>" + investment[i] + "</td>");
	}
	out.print("</tr></table>");
	%>
	<h1>ENERGY GENERATION REPORT</h1>
	<%
	out.println("Thankyou for using Solar Power Calculator... <br/>");
	out.println("Generating report now...<br/>");
	out.println("Your machine's address is ");
	out.println(request.getRemoteHost());
	java.util.Date date = new java.util.Date();
	out.println("<br/>Report generated on "); 
	out.println(date);
%>
	<br />Insert report here !----------BeginFooter Area----------! Insert
	disclaimer here !----------End Footer Area----------!
</body>

</html>