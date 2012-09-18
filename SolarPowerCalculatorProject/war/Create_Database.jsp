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

 db.setEnergy("Click Energy", 0.05, 0.2538, 0.1715);
db.setEnergy("Integral Energy", 0.05, 0.25378, 0.17155);
db.setEnergy("Origin", 0.05, 0.253781, 0.171545);

db.setPanel("SOLARLAND", "SLP135-24", 1.5, 0.675, 999, 0.12015, 0.135);
db.setPanel("SOLARLAND", "SLP195S-24", 1.58, 0.808, 999, 0.175695, 0.195);
db.setPanel("SOLARLAND", "SLP135-24", 1.64, 0.992, 999, 0.21252, 0.23);
db.setPanel("SOLON", "BLACK230/07", 1.640, 1, 1000, 0.186, 0.260);
db.setPanel("SOLON", "BLUE230/07", 1.640, 1, 1000, 0.190, 0.260);
db.setPanel("REC", "Rec250peBLK", 1.665, 0.991, 1000, 0.182, 0.250);
db.setPanel("REC", "Rec255pePLUS", 1.665, 0.991, 1000, 0.193, 0.255);
db.setPanel("REC", " REC215PE", 1.665, 0.991, 1000, 0.19565, 0.215);
db.setPanel("Lorentz", "LA130-24S", 1.43, 0.636, 1000, 0.12012, 0.13);
db.setPanel("Lorentz", "LA160-24S", 1.556, 0.669, 1000, 0.14784, 0.16);
db.setPanel("Conergy", "C175M", 1.575, 0.826, 1000, 0.15925, 0.175);
db.setPanel("Sanyo", "HIP-210NKHE6", 1.58, 0.798, 1000, 0.1995, 0.21);
db.setPanel("Sanyo", "MP6-230E01", 1.658, 0.986, 1000, 0.2093, 0.23);
db.setPanel("SunPower", "SPR-200", 1.559, 0.798, 1000, 0.1848, 0.2);
db.setPanel("SunPower", "SPR-210", 1.559, 0.798, 1000, 0.19404, 0.21);
db.setPanel("SunPower", "SPR-225", 1.559, 0.798, 1000, 0.2079, 0.225);
db.setPanel("SunPower", "SPR-300W", 1.559, 1.046, 1000, 0.2772, 0.3);
db.setPanel("Suntech", "STP170S-24Ad", 1.2, 0.8, 1000, 0.151232, 0.17);
db.setPanel("Suntech", "STP180S24AdBlack", 1.58, 0.808, 1000, 0.16272, 0.18);
db.setPanel("Suntech", "STP190-18Ub", 1.2, 0.8, 1000, 0.17214, 0.19);
db.setPanel("Suntech", "STP250-20/Wd", 1.64, 0.992, 1000, 0.184, 0.25);

db.setInverter("SMA", "SB1700", 1.85, 0.935, 1700, 1.55, 400);
db.setInverter("SMA", "SB3000", 3.15, 0.963, 2500, 3.0, 700);
db.setInverter("Conergy", "WR1700", 1.5, 0.942, 1500, 1.3, 500);
db.setInverter("SMA", "SB5000TL", 5.3, 0.97, 3000, 5.0, 550); 

/* String shit = db.getInverterBrandByInverterSerialNumber("SB1700");
System.out.println(shit);
shit = db.getSolarPanelBrandByPanelSerialNumber("Rec255pePLUS");
System.out.println(shit);

ArrayList<String> energyProviders = db.getAllInverterSerials();
for(String provider : energyProviders){
	System.out.println(provider);
}

energyProviders = db.getAllSolarPanelSerials();
for(String provider : energyProviders){
	System.out.println(provider);
} */


/*db.setEnergy("Origin", 5, .234, .123);
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
System.out.println(next14); 

*/

%>
</body>
</html>