package project.server;

public class Tariffs {
	
	private String supplierName;
	//dollars per kW
	private double tariff11;	
	private double tariff33;
	private double feedInTariff;
	//dollars per day
	private double tariff11SupplyCharge;
	//percent
	private int annualTariffIncrease;
	//default
	public Tariffs(){
		
		supplierName = "Origin";
		tariff11 = 0.253781;
		tariff11SupplyCharge = 0.287870;
		tariff33 = 0.171545;
		//feed in tariff = 8c/kWh for <5kW inverters
		//feedInTariff = 6c/kWh for <100kW inverters
		feedInTariff = 0.08;	
		annualTariffIncrease = 5;
	}
	
	public Tariffs(String supplierName){
		//TODO get data from datastore
	}
	
	public double getNormalTariff(){
		return tariff11;
	}
	
	public double getOffPeakTariff(){
		return tariff33;
	}
	
	public double getFeedInTariff(){
		return feedInTariff;
	}
	
	public int getAnnualTariffIncrease(){
		return annualTariffIncrease;
	}
}
