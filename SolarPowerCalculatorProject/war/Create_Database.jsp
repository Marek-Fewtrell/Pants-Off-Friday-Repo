<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import = "project.server.Database" import = "java.util.Scanner" import = "java.util.ArrayList"
    import = "java.util.List" import = "java.io.IOException" import = "java.io.FileReader"
   import = "java.io.BufferedReader" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Database db = new Database();

List<String> _suburb = new ArrayList<String>();
List<String> _latitude = new ArrayList<String>();
List<String> _solar = new ArrayList<String>();
BufferedReader br = null;
try {
	br = new BufferedReader(new FileReader("QLD.txt"));

	String word;
	String[] awordList;
	while ((word = br.readLine()) != null){
		
		awordList = word.split("\t");
		_suburb.add(awordList[0]);
		_latitude.add(awordList[1]);
		_solar.add(awordList[2]);
	}
} catch (IOException e) {
	e.printStackTrace();
} finally {
	try {
		br.close();
	} catch (IOException ex) {
		ex.printStackTrace();
	}
}

String[] suburb = new String[_suburb.size()];
String[] latitude = new String[_suburb.size()];
String[] exposure = new String[_suburb.size()];

_suburb.toArray(suburb);
_latitude.toArray(latitude);
_solar.toArray(exposure);

for (int i = 0; i < suburb.length; i++){
	
	db.setSunData(suburb[i], Double.parseDouble(exposure[i]), Double.parseDouble(latitude[i]));
	
}

/*  db.setEnergy("Origin", 5, .234, .123);
db.setEnergy("CLick Energy", 4, .345, .876);
db.setEnergy("Energyex", 5, .567, .493);

db.setInverter("Good Inverter", "GI123", 342, 34, 564, 32, 67);
db.setInverter("Bad Inverter", "BI321", 574, 234, 4546, 2342, 432);
db.setInverter("Bad Inverter", "BI123", 432, 342, 44, 234, 55);
db.setInverter("Average Inverter", "AI987", 435, 565, 242, 8, 453);


db.setPanel("Good Panel", "GP123", 145, 20, 400, 47, 93);
db.setPanel("Bad Panel", "BP321", 392, 937, 666, 388, 432);
db.setPanel("Average Panel", "AP987", 392, 3294, 343, 292, 234);
db.setPanel("Average Panel", "AP345", 456, 3, 58, 3.4, 222);

db.setSunData("McDowall", 40, 27);
db.setSunData("Aspley", 37, 27);
db.setSunData("Bridgman Downs", 50, 26); 



ArrayList<String> energyProviders = db.getAllEnergyProviders();
for(String provider : energyProviders){
	System.out.println(provider);
}

String CEthing = Double.toString(db.getAnnualTariffIncrease("CLick Energy"));
System.out.println(CEthing);

String next = Double.toString(db.getTariff11("Origin"));
System.out.println(next);

String next2 = Double.toString(db.getTariff33("Energyex"));
System.out.println(next2);

System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

ArrayList<String> suburbs = db.getAllSuburbs();
for(String suburb : suburbs){
	System.out.println(suburb);
}

String next3 = Double.toString(db.getSolarExposure("Aspley"));
System.out.println(next3);

String next4 = Double.toString(db.getLatitude("McDowall"));
System.out.println(next4);
System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

ArrayList<String> Inveters = db.getAllInverterBrands();
for(String inverter : Inveters){
	System.out.println(inverter);
}

ArrayList<String> InverSerials = db.getInverterSerialNumberByBrand("Bad Inverter");
for(String serail : InverSerials){
	System.out.println(serail);
}

String next5 = Double.toString(db.getInverterPMax("BI123"));
System.out.println(next5);

String next6 = Double.toString(db.getInverterMaxEfficiency("GI123"));
System.out.println(next6);
String next7 = Double.toString(db.getInverterNomACOutput("AI987"));
System.out.println(next7);
String next8 = Double.toString(db.getInverterPrice("BI123"));
System.out.println(next8);
String next9 = Double.toString(db.getInverterVMax("BI321"));
System.out.println(next9);

System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

ArrayList<String> panels = db.getAllSolarPanelBrands();
for(String panel : panels){
	System.out.println(panel);
}

ArrayList<String> panelSerial = db.getSolarPanelSerialNumberByBrand("Average Panel");
for(String panel : panelSerial){
	System.out.println(panel);
}

String next10 = Double.toString(db.getPanelLength("GP123"));
System.out.println(next10);
String next11 = Double.toString(db.getPanelNOCT("BP321"));
System.out.println(next11);
String next12 = Double.toString(db.getPanelPMax("AP987"));
System.out.println(next12);
String next13 = Double.toString(db.getPanelPrice("AP345"));
System.out.println(next13);
String next14 = Double.toString(db.getPanelWidth("GP123"));
System.out.println(next14); */



%>
</body>
</html>