<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculator</title>
</head>
<body>

<h2>Calculator Input:</h2>

<form method=post action='CalculatorServlet' id="form1" >

<select name="solarPanel">
<option>Select solar panel</option>
<option>REC230PE</option>
</select></br>
<label>Enter number of panels:</label>
<input name="numPanels" maxlength=2></br>
<select name="inverter">
<option>Select inverter</option>
<option>SB1600TL</option>
</select></br>
<label>Enter electricity used during day: (kW)</label>
<input name="daytimeUsage"></br>
<label>Enter efficiency loss due to panel location: (%)</label>
<input name="efficiencyLoss" maxlength=2></br>
<label>Enter postcode:</label>
<input name="postcode" maxlength=4>
<label>Enter suburb:</label>
<input name="suburb"></br>
<select name="energyProvider">
<option>Select energy provider</option>
<option>Origin</option>
</select></br>
<label><input /></label>
<label>Rated power of the solar panel (W):</label><br/>
<label>Last Quarter Bill</label><br/>
<label>Cost per kWh</label><br/>
<label>Cost of Avg energy bill</label><br/>
<label></label><br/>
<label>Roof Angle</label><br/>
<label>Direction of intended roof face</label><br/>
<label>Power used during daylight hours</label><br/>

<label>System Size (kW)</label><br/>
<label>Daily Average Usage</label><br/>
<label>Day Time hourly usage</label><br/>
<label></label><br/>
<label></label><br/>

<input type="submit" value="Submit" name="submit"/>
</form>

</body>
</html>