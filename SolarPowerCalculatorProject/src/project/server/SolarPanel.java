package project.server;

public class SolarPanel {

	private String serialNumber;
	private String brand;
	private int pMax;
	private int pMaxNOCT;
	private int price;
	private int length;
	private int width;
	
	//default panel
	public SolarPanel(){
		serialNumber = "REC230PE";
		brand = "REC";
		pMax = 230;
		pMaxNOCT = 175;
		price = 450;
		length = 1665;
		width = 991;
	}
	
	public SolarPanel(String serialNumber){
		//TODO get data from datastore
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public int getPMax(){
		return pMax;
	}
	
	public int getPMaxNOCT(){
		return pMaxNOCT;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getLength(){
		return length;
	}
}
