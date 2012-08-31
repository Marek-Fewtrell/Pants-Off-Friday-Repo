package project.server;

public class Inverter {

	private String serialNumber;
	private String brand;
	private int price;
	//Watts
	private int pMax;
	//Volts
	private int vMax;
	//Watts
	private int nomACOutput;
	//percent
	private double maxEfficiency;
	//default 
	public Inverter(){
		brand = "SMA";
		serialNumber = "SB1600TL";
		pMax = 1700;
		vMax = 600;
		nomACOutput = 1600;
		maxEfficiency = 0.96;
		price = 990;
		
	}
	
	public Inverter(String serialNumber){
		//TODO get data from datastore
	}
	
	public int getPMax(){
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
