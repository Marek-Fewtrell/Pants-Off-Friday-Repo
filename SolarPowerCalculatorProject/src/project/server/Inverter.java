package project.server;

public class Inverter {

	private String serialNumber;
	private String brand;
	private int price;
	private int pMax;
	private int vMax;
	private int nomACOutput;
	private double maxEfficiency;
	//default 
	public Inverter(){
		brand = "SMA";
		serialNumber = "SB1600TL";
		pMax = 1700;
		vMax = 600;
		nomACOutput = 1600;
		maxEfficiency = 96;
		price = 990;
		
	}
	
	public Inverter(String serialNumber){
		//TODO get data from datastore
	}
}
