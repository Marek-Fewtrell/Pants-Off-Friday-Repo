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
/* Start added by Marek 18/09/2012 */
/* db.setInverter(Company Name, Model Number, 
Max Output, Max Efficiency, price, nominal Output, vMax*/
db.setInverter("Conergy", "WR2300", 2.0, 0.943, 1900, 1.8, 500); 
db.setInverter("Conergy", "WR3300", 2.65, 0.943, 2000, 2.5, 500); 
db.setInverter("Conergy", "WR4600", 4.1, 0.943, 3100, 3.5, 500); 
db.setInverter("Conergy", "WR5900", 5.0, 0.943, 2200, 4.6, 530); 

db.setInverter("Fronius", "IG15", 1.5, 0.942, 1500, 1.3, 500); 
db.setInverter("Fronius", "IG20", 2.0, 0.943, 1500, 1.8, 500); 
db.setInverter("Fronius", "IG30", 2.6, 0.943, 2600, 2.5, 500); 
db.setInverter("Fronius", "IG40", 4.1, 0.943, 3100, 3.5, 500); 
db.setInverter("Fronius", "IG300", 24.0, 0.943, 6000, 24.0, 530); 
db.setInverter("Fronius", "IG500", 40.0, 0.943, 12000, 40.0, 530); 

db.setInverter("KACO", "1501xi", 1.5, 0.95, 1300, 1.5, 400); 
db.setInverter("KACO", "3501xi", 3.3, 0.95, 1800, 3.3, 400); 
db.setInverter("KACO", "4501xi", 4.6, 0.95, 2600, 4.6, 400); 

db.setInverter("Latronics", "PVE1200", 1.2, 0.93, 1900, 1.0, 100); 
db.setInverter("Latronics", "PVE2500", 3.1, 0.93, 3500, 2.1, 200);

db.setInverter("Motech", "3300MS", 3.6, 0.963, 2000, 3.3, 500);
db.setInverter("Motech", "3800MS", 4.2, 0.963, 2700, 3.8, 500);
db.setInverter("Motech", "4600MS", 5.1, 0.963, 2900, 4.6, 500);

db.setInverter("PowerRouter", "PR30S", 3.3, 0.945, 2100, 3.0, 480);
db.setInverter("PowerRouter", "PR37S", 4.0, 0.945, 2600, 3.7, 480);
db.setInverter("PowerRouter", "PR50S", 5.5, 0.945, 3100, 5.0, 480);

db.setInverter("Omnik", "1.5kTL", 1.75, 0.974, 1400, 1.5, 500);
db.setInverter("Omnik", "2kTL", 2.3, 0.974, 2000, 2.0, 500);

db.setInverter("Power-One", "PVI-6000-OUTD", 6.9, 0.97, 2300, 6.2, 600);
db.setInverter("Power-One", "PVI-5000-OUTD-AU", 5.75, 0.97, 2600, 4.8, 600);
db.setInverter("Power-One", "PVI-4.2-OUTD", 4.82, 0.968, 2300, 4.38, 600);
db.setInverter("Power-One", "PVI-3.0-OUTD", 3.5, 0.968, 1700, 3.12, 600);
db.setInverter("Power-One", "PVI-2000-OUTD", 2.3, 0.96, 1200, 2.1, 600);

db.setInverter("SMA", "SB700", 0.7, 0.934, 875, 0.6, 250);
db.setInverter("SMA", "SB1100", 1.1, 0.93, 1100, 1.0, 400);
db.setInverter("SMA", "SB1200", 1.3, 0.921, 1300, 1.2, 400);
db.setInverter("SMA", "SB1700", 1.7, 0.935, 1400, 1.5, 400);
db.setInverter("SMA", "SB2500", 2.5, 0.941, 1800, 2.3, 600);
db.setInverter("SMA", "SB3300", 3.6, 0.952, 2000, 3.3, 500);
db.setInverter("SMA", "SB3800", 3.8, 0.956, 2100, 3.8, 500);
db.setInverter("SMA", "SMC5000A", 5.5, 0.961, 2700, 5.0, 600);
db.setInverter("SMA", "SMC6000A", 6.0, 0.961, 2800, 6.0, 600);
db.setInverter("SMA", "SMC7000TL", 7.0, 0.98, 2900, 7.0, 700);
db.setInverter("SMA", "SMC10000TL", 10.0, 0.98, 4300, 10.0, 700);
db.setInverter("SMA", "SMC11000TL", 11.0, 0.98, 4300, 11.0, 700);

db.setInverter("Xantrex", "FT2.8-AU", 2.8, 0.95, 1680, 2.3, 600);

/* End added by Marek 18/09/2012 */

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