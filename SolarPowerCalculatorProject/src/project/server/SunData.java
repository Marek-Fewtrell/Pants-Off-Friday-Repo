package project.server;

public class SunData {
	private Database db;
	//maybe string
	private int postcode;
	private String suburb;
	//only going to use one of these 
	//depending on whether we can work
	//out how to calculate using solarExposure
	private double solarExposure;//kwH/m^2
	private double sunlightHours;
	private double latitude;
	//maybe add monthly stats later
	
	//default
	public SunData(){
		postcode = 4000;
		suburb = "Brisbane";
		solarExposure = 5.3;
		latitude = 27;
	}
	
	public SunData(String suburb){
		db = new Database();
		this.postcode = postcode;
		//
		//
		//
		solarExposure = db.getSolarExposure(suburb);
		latitude = db.getLatitude(suburb);
	}
	
	public double getSolarExposure(){
		return solarExposure;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
}
