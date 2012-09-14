package project.server;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Database {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/*-------------------------------------------------------------*/
						  /*start Tariff*/
	
	public void setEnergy(String name, double amount, double price11, double price33){
		Entity A_DS = new Entity("Companies", name);
		A_DS.setProperty("Increase", amount);
		A_DS.setProperty("Price33", price33);
		A_DS.setProperty("Price11", price11);
		datastore.put(A_DS);
	}
	
	
	public double getTariff11(String name){
		double tariff11 = 0;
		Query q = new Query("Companies");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(name)) {
				if(result.getProperty("Price11") != null){
				tariff11 = (Double) result.getProperty("Price11");
			}
			}
	}
		return tariff11;
	}
	
	public double getTariff33(String name){
		double tariff33 =  0.171545;
		Query q = new Query("Companies");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(name)) {
				if(result.getProperty("Price33") != null){
				tariff33 = (Double) result.getProperty("Price33");
			}
			}
	}
		return tariff33;
	}
	
	
	public double getAnnualTariffIncrease(String name){
		double annualTariffIncrease = 0;
		Query q = new Query("Companies");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(name)) {
				if(result.getProperty("Increase") != null){
				annualTariffIncrease = (Double) result.getProperty("Increase");
			}
			}
	}
		return annualTariffIncrease;
	}
	
	public ArrayList<String> getAllEnergyProviders(){
		ArrayList<String> energyProviders = new ArrayList<String>();	
		Query q = new Query("Companies");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			energyProviders.add(counter, result.getKey().getName());
			counter++;
	}
		return energyProviders;
	}
					    	/*end Tariff*/
	/*-------------------------------------------------------------*/
	 
	
	
	/*-------------------------------------------------------------*/
	                     /*start sun data*/
	public void setSunData(String suburb, double exposure, double latitude){
		Entity A_DS = new Entity("Sun_data", suburb);
		A_DS.setProperty("Exposure", exposure);
		A_DS.setProperty("Latitude", latitude);
		datastore.put(A_DS);
	}
	
	public ArrayList<String> getAllSuburbs(){
		ArrayList<String> suburbs = new ArrayList<String>();
		Query q = new Query("Sun_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			suburbs.add(counter, result.getKey().getName());
			counter++;
	}
		return suburbs;
	}
	
	
	public double getSolarExposure(String suburb){
		double solarExposure = 0;
		Query q = new Query("Sun_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(suburb)) {
				if(result.getProperty("Exposure") != null){
				solarExposure = (Double) result.getProperty("Exposure");
			}
			}
	}
		return solarExposure;
	}
	
	
	public double getLatitude(String suburb){
		double latitude = 0;
		Query q = new Query("Sun_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(suburb)) {
				if(result.getProperty("Latitude") != null){
				latitude = (Double)(result.getProperty("Latitude"));
			}
			}
	}
		return latitude;
	}
	                       /*end SunData*/
	/*-------------------------------------------------------------*/

	
	
	/*-------------------------------------------------------------*/
                         /*start inverter*/
	
	public void setInverter(String brand, String serial, double pMax, double maxEfficiency, double price, double nomACOutput, double vMax){
		Entity A_DS = new Entity("Inverter_data", serial);
		A_DS.setProperty("PMax", pMax);
		A_DS.setProperty("MaxEfficiency", maxEfficiency);
		A_DS.setProperty("Brand", brand);
		A_DS.setProperty("Price", price);
		A_DS.setProperty("NomACOutput", nomACOutput);
		A_DS.setProperty("VMax", vMax);
		datastore.put(A_DS);
	}
	
	public String getInverterBrandByInverterSerialNumber(String serial){
		String brand = "";
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		String compair = "";
		for (Entity result : pq.asIterable()) {
			compair = result.getKey().getName();
			if(compair.equals(serial)) {
			brand = (String)result.getProperty("Brand");
			}
	}
		return brand;
	}
	
	public ArrayList<String> getInverterSerialNumberByBrand(String brand){
		ArrayList<String> serialNumbers = new ArrayList<String>();
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		String compair = "";
		for (Entity result : pq.asIterable()) {
			compair = (String)result.getProperty("Brand");
			if(compair.equals(brand)) {
			serialNumbers.add(counter, result.getKey().getName());
			counter++;
			}
	}
		return serialNumbers;
	}
	
	public ArrayList<String> getAllInverterBrands(){
		ArrayList<String> brands = new ArrayList<String>();
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			if(brands.contains((String)result.getProperty("Brand")) == false){
			brands.add(counter, (String)result.getProperty("Brand"));
			counter++;
			}
	}
		return brands;
	}
	
	public ArrayList<String> getAllInverterSerials(){
		ArrayList<String> serials = new ArrayList<String>();
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			serials.add(counter, result.getKey().getName());
			counter++;
	}
		return serials;
	}
	
	
	public double getInverterPMax(String serial){
		double pMax = 0;
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("PMax") != null){
					pMax = (Double) result.getProperty("PMax");
			}
			}
	}
		return pMax;
	}
	

	public double getInverterMaxEfficiency(String serial){
		double maxEfficiency = 0;
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("MaxEfficiency") != null){
					maxEfficiency = (Double) result.getProperty("MaxEfficiency");
			}
			}
	}
		return maxEfficiency;
	}
	

	public double getInverterPrice(String serial){
		double price = 0;
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("Price") != null){
					price = (Double) result.getProperty("Price");
			}
			}
	}
		return price;
	}
	
	public double getInverterNomACOutput(String serial){
		double nomACOutput = 0;
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("NomACOutput") != null){
					nomACOutput = (Double) result.getProperty("NomACOutput");
			}
			}
	}
		return nomACOutput;
	}
	
	public double getInverterVMax(String serial){
		double vMax = 0;
		Query q = new Query("Inverter_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("VMax") != null){
					vMax = (Double) result.getProperty("VMax");
			}
			}
	}
		return vMax;
	}
	                      /*end inverter*/
	/*-------------------------------------------------------------*/
  
	 
	
	/*-------------------------------------------------------------*/
                           /*start panel*/
	
	public void setPanel(String brand, String serial, double length, double width, double price, double pMaxNOCT, double pMax){
		Entity A_DS = new Entity("Panel_data", serial);
		A_DS.setProperty("PMax", pMax);
		A_DS.setProperty("PMaxNOCT", pMaxNOCT);
		A_DS.setProperty("Price", price);
		A_DS.setProperty("Length", length);
		A_DS.setProperty("Width", width);
		A_DS.setProperty("Brand", brand);
		datastore.put(A_DS);
	}
	
	public ArrayList<String> getSolarPanelSerialNumberByBrand(String brand){
		ArrayList<String> serialNumbers = new ArrayList<String>();
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		String compair = "";
		for (Entity result : pq.asIterable()) {
			compair = (String)result.getProperty("Brand");
			if(compair.equals(brand)) {
			serialNumbers.add(counter, result.getKey().getName());
			counter++;
			}
	}
		return serialNumbers;
	}
	
	public String getSolarPanelBrandByPanelSerialNumber(String serial){
		String brand = "";
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		String compair = "";
		for (Entity result : pq.asIterable()) {
			compair = result.getKey().getName();
			if(compair.equals(serial)) {
			brand = (String)result.getProperty("Brand");
			}
	}
		return brand;
	}
	
	public ArrayList<String> getAllSolarPanelBrands(){
		ArrayList<String> brands = new ArrayList<String>();
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			if(brands.contains((String)result.getProperty("Brand")) == false){
			brands.add(counter, (String)result.getProperty("Brand"));
			counter++;
			}
	}
		return brands;
	}

	public ArrayList<String> getAllSolarPanelSerials(){
		ArrayList<String> serials = new ArrayList<String>();
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		int counter = 0;
		for (Entity result : pq.asIterable()) {
			serials.add(counter, result.getKey().getName());
			counter++;
	}
		return serials;
	}
	
	public double getPanelPMax(String serial){
		double pMax = 0;
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("PMax") != null){
					pMax = (Double) result.getProperty("PMax");
			}
			}
	}
		return pMax;
	}
	

	public double getPanelNOCT(String serial){
		double pMaxNOCT = 0;
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("PMaxNOCT") != null){
					pMaxNOCT = (Double) result.getProperty("PMaxNOCT");
			}
			}
	}
		return pMaxNOCT;
	}

	
	public double getPanelPrice(String serial){
		double price = 0;
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("Price") != null){
					price = (Double) result.getProperty("Price");
			}
			}
	}
		return price;
	}
	
	
	
	public double getPanelLength(String serial){
		double length = 0;
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("Length") != null){
					length = (Double) result.getProperty("Length");
			}
			}
	}
		return length;
	}
	
	public double getPanelWidth(String serial){
		double width = 0.991;
		Query q = new Query("Panel_data");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			if(result.getKey().getName().equals(serial)) {
				if(result.getProperty("Width") != null){
					width = (Double) result.getProperty("Width");
			}
			}
	}
		return width;
	}
                             /*end panel*/
	/*-------------------------------------------------------------*/


}