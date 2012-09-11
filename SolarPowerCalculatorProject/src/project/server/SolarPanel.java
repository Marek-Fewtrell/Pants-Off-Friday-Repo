package project.server;

public class SolarPanel {

	private String serialNumber;
	private String brand;
	//kiloWatts
	private double pMax;
	private double pMaxNOCT;
	private double price;
	//m
	private double length;
	private double width;
	private double area;
	private Database db = new Database();
	
	//default panel
	public SolarPanel(){
		serialNumber = "REC230PE";
		brand = "REC";
		pMax = 0.230;
		pMaxNOCT = 0.175;
		price = 450;
		length = 1.665;
		width = 0.991;
		area = length * width;
	}
	
	public SolarPanel(String serialNumber){
		//TODO get data from datastore
		this.serialNumber = serialNumber;
		pMax = db.getPanelPMax(serialNumber);
		pMaxNOCT = db.getPanelNOCT(serialNumber);
		price = db.getPanelPrice(serialNumber);
		length = db.getPanelLength(serialNumber);
		width = db.getPanelWidth(serialNumber);
		area = length * width;
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public double getPMax(){
		return pMax;
	}
	
	public double getPMaxNOCT(){
		return pMaxNOCT;
	}
	
	public double getPrice(){
		return price;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getLength(){
		return length;
	}
	
	public double getArea(){
		return area;
	}
}
