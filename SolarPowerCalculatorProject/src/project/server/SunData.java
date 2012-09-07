package project.server;

public class SunData {
	//maybe string
	private int postcode;
	private String suburb;
	//only going to use one of these 
	//depending on whether we can work
	//out how to calculate using solarExposure
	private double solarExposure;//kwH/m^2
	private double sunlightHours;
	private int latitude;
	//maybe add monthly stats later
	
	//default
	public SunData(){
		postcode = 4000;
		suburb = "Brisbane";
		solarExposure = 5.3;
	}
	
	public SunData(int postcode, String suburb){
		//TODO get solar exposure data from datastore
	}
	
	public double getSolarExposure(){
		return solarExposure;
	}
	
}
