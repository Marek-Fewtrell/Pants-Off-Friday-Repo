package project.server;

public class Inverter {

	private Database db;
	private String serialNumber;
	private String brand;
	private int price;
	//kiloWatts
	private double pMax;
	//Volts
	private int vMax;
	//kiloWatts
	private double nomACOutput;
	//percent
	private double maxEfficiency;
	//default 
	public Inverter(){
		brand = "SMA";
		serialNumber = "SB1600TL";
		pMax = 1.700;
		vMax = 600;
		nomACOutput = 1.600;
		maxEfficiency = 0.96;
		price = 990;
		
	}
	
	public Inverter(String serialNumber){
		db = new Database();
		this.serialNumber = serialNumber;
		//
		//edit
		//
		pMax = db.getInverterPMax(serialNumber);
		maxEfficiency = db.getInverterMaxEfficiency(serialNumber);
	}
	
	public double getPMax(){
		return pMax;
	}
	
	public int getVMax(){
		return vMax;
	}
	
	public double getMaxEfficiency(){
		return maxEfficiency;
	}
	
	public int getPrice(){
		return price;
	}
}
