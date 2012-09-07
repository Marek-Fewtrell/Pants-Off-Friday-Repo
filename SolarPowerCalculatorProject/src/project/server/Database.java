package project.server;



import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Database {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//	public void addPannel(String panelID, int width){
//		
//		
//		
//		Entity panel = new Entity(panelID);
//
//		panel.setProperty("ID", panelID);
//		panel.setProperty("width", width);
//		//insert more
//		datastore.put(panel);
//		
//	}
//	
//	public int getWidth(String panelID){
//		int width = 0;
//		
//		Query q = new Query(panelID);
//		PreparedQuery pq = datastore.prepare(q);
//		Entity result = pq.asSingleEntity();
//		width = Integer.parseInt((String)result.getProperty("width"));
//		
//		return width;
//	}
	
	public double getTariff11(String name){
		double tariff11 = 0.253781;
		return tariff11;
	}
	
	public double getTariff11SupplyCharge(String name){
		double tariff11SupplyCharge = 0.287870;
		return tariff11SupplyCharge;
	}
	
	public double getTariff33(String name){
		double tariff33 =  0.171545;
		return tariff33;
	}
	/*
	 * this is a standard rate in queensland i believe
	 * 
	public double getFeedInTariff(String name){
		double feedInTariff = 0.08;
		return feedInTariff;
	}
	*/
	public double getAnnualTariffIncrease(String name){
		double annualTariffIncrease = 5;
		return annualTariffIncrease;
	}
	
	public ArrayList<String> getAllEnergyProviders(){
		ArrayList<String> energyProviders = new ArrayList<String>();
		energyProviders.add(0, "Origin");
		energyProviders.add(1, "Energex");
		energyProviders.add(2, "Red");
		energyProviders.add(3, "AGL");
		return energyProviders;
	}
	
	public ArrayList<String> getAllSuburbs(){
		ArrayList<String> suburbs = new ArrayList<String>();
		suburbs.add(0, "Chermside");
		suburbs.add(1, "Aspley");
		suburbs.add(2, "Bridgeman Downs");
		suburbs.add(3, "McDowall");
		return suburbs;
	}
	
	public double getSolarExposure(int postcode){
		double solarExposure = 5.3;
		return solarExposure;
	}
	
	public int getLatitude(int postcode){
		int latitude = 27;
		return latitude;
	}
	
	public ArrayList<String> getInverterSerialNumberByBrand(String brand){
		ArrayList<String> serialNumbers = new ArrayList<String>();
		serialNumbers.add(0, "dksh1234");
		serialNumbers.add(1, "qwer4321");
		serialNumbers.add(2, "zxcv0987");
		serialNumbers.add(3, "lkjh8765");
		return serialNumbers;
	}
	
	public ArrayList<String> getAllInverterBrands(){
		ArrayList<String> brands = new ArrayList<String>();
		brands.add(0, "Sun");
		brands.add(1, "other3");
		brands.add(2, "good panels");
		brands.add(3, "solar is us");
		return brands;
	}
	
	public double getInverterPMax(String serial){
		double pMax = 1.700;
		return pMax;
	}
	
	public double getInverterMaxEfficiency(String serial){
		double maxEfficiency = 0.96;
		return maxEfficiency;
	}
	
	
}