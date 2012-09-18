<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="project.server.Database" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculator</title>
</head>
<body>
<%
Database db = new Database();
%>
	<h2>Calculator Input:</h2>

	<form method="post" action='CalculatorServlet' id="form1">

		<select name="solarPanel">
			<option>Select solar panel</option>
			<%
			ArrayList<String>panels = new ArrayList<String>();
			panels = db.getAllSolarPanelSerials();
			for(int i=0; i<panels.size(); i++){
				out.print("<option>" + panels.get(i) +"</option>");
			}
			%>
		</select> <label>Enter number of panels:</label> <input name="numPanels"
			maxlength=2> <select name="inverter">
			<option>Select inverter</option>
			<%
			ArrayList<String>inverters = new ArrayList<String>();
			inverters = db.getAllInverterSerials();
			for(int i=0; i<inverters.size(); i++){
				out.print("<option>" + inverters.get(i) +"</option>");
			}
			%>
			
		</select> <label>Enter electricity used during day: (kW)</label> <input
			name="daytimeUsage"> <label>Enter tilt angle:</label> <input name="tiltAngle" maxlength=2>
		<label>Enter direction panel is facing: (degrees)</label><input name = "orientation">
		<label>Enter latitude: (degrees)</label><input name = "latitude">
		<label>Enter postcode:</label> <input name="postcode" maxlength=4>
		<label>Enter suburb:</label> <input name="suburb"> <select
			name="energyProvider">
			<option>Select energy provider</option>
			<%
			
			ArrayList <String> energyCompanies = new ArrayList<String>();
			energyCompanies = db.getAllEnergyProviders();
			for(int i=0; i<energyCompanies.size(); i++){
				out.print("<option>" + energyCompanies.get(i) + "</option>");
			}
			%>
		</select> 
		<label>Enter initial installation cost:</label><input name="installationCost">
		<label>Enter interest rate: (%)</label><input name="interestRate">
		<label>Rated power of the solar
			panel (W):</label><br /> <label>Last Quarter Bill</label><br /> <label>Cost
			per kWh</label><br /> <label>Cost of Avg energy bill</label><br /> <label></label><br />
		<label>Roof Angle</label><br /> <label>Direction of intended
			roof face</label><br /> <label>Power used during daylight hours</label><br />

		<label>System Size (kW)</label><br /> <label>Daily Average
			Usage</label><br /> <label>Day Time hourly usage</label><br /> <label></label><br />
		<label></label><br /> <input type="submit" value="Submit"
			name="submit" />
	</form>



</body>
</html>
