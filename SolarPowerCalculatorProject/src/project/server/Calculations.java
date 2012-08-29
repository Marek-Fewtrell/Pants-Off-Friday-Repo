package project.server;

public class Calculations {
	
	private Inverter inverter;
	private SolarPanel panel;
	private SunData sunData;
	private Tariffs tariffs;
	private int systemPower;
	private double orientationEfficiencyLoss;
	private double inverterEfficiency;
	private double solarExposure;
	private double powerGenerated;

	
	public Calculations(String panelNumber, int numberPanels, String suburb,
			String inverterNumber, String energyCompany){
		//TODO change all constructors later
		inverter = new Inverter();
		panel = new SolarPanel();
		sunData = new SunData();
		tariffs = new Tariffs();
		//change to calculated value
		orientationEfficiencyLoss = 0.10;
		//find out if this is how it works
		systemPower = inverter.getPMax();
		if(panel.getPMax()*numberPanels < systemPower){
			systemPower = panel.getPMax()*numberPanels;
		}
		
		inverterEfficiency = inverter.getMaxEfficiency();
		solarExposure = sunData.getSolarExposure();
		
		//daily generation
		//needs verification and solar panel age efficiency loss
		powerGenerated = systemPower * (1 - orientationEfficiencyLoss) * 
				inverterEfficiency * solarExposure;
	}
}
