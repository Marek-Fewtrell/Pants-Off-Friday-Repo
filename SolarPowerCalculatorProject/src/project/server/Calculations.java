package project.server;

public class Calculations {
	
	private Inverter inverter;
	private SolarPanel panel;
	private SunData sunData;
	private Tariffs tariffs;
	private double systemPower;
	private int numberPanels;
	private double dailySavings;
	private double orientationEfficiencyLoss;
	private double inverterEfficiency;
	private double solarExposure;
	private double powerGenerated;
	private double tariff11;
	private double feedInTariff;
	private double replacementGeneration;
	private static double DAYSPERYEAR = 365.25;
	private double [] cumulative;

	
	public Calculations(String panelNumber, int numberPanels, String suburb,
			String inverterNumber, String energyCompany, Double dailyUsage, 
			Double efficiencyLoss){
		//TODO change all constructors later
		inverter = new Inverter();
		panel = new SolarPanel();
		sunData = new SunData();
		tariffs = new Tariffs();
		tariff11 = tariffs.getNormalTariff();
		feedInTariff = tariffs.getFeedInTariff();
		this.numberPanels = numberPanels;
		//change to a calculated value
		orientationEfficiencyLoss = efficiencyLoss/100;
		//find out if this is how it works
		systemPower = inverter.getPMax();
		//change this to user entered data
		replacementGeneration = dailyUsage;
		inverterEfficiency = inverter.getMaxEfficiency();
		solarExposure = sunData.getSolarExposure();
	}
	//use year to adjust efficiency
	public double getPowerGenerated(int year){
		
		if(panel.getPMaxNOCT()*numberPanels < systemPower){
			systemPower = panel.getPMaxNOCT()*numberPanels;
		}
				
		//daily generation
		//needs verification and solar panel age efficiency loss
		powerGenerated = systemPower * (1 - orientationEfficiencyLoss) * 
				inverterEfficiency * solarExposure;
		return powerGenerated;
	}
	
	public double getDailySavings(int year){
		//in dollars
		double power = this.getPowerGenerated(year);
		if(replacementGeneration < power){
			dailySavings = replacementGeneration * tariff11 + 
				(power - replacementGeneration)
				* feedInTariff;
		}else{
			dailySavings = power * tariff11;
		}
		return dailySavings;
	}
	//maybe just return a single value??
	public double[] getCumulativeSavings(int numYears){
		cumulative = new double[numYears];
		cumulative[0] = getDailySavings(1)*DAYSPERYEAR;
		for (int i=1; i<numYears; i++){
			cumulative[i] = cumulative[i-1] + getDailySavings(i+1)*DAYSPERYEAR;
		}
		return cumulative;
	}
}
