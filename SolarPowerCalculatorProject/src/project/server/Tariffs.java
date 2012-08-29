package project.server;

public class Tariffs {
	
	private String supplierName;
	private double tariff11;
	//cents per day
	private double tariff11SupplyCharge;
	private double tariff33;
	private double feedInTariff;
	private int annualTariffIncrease;
	//default
	public Tariffs(){
		
		supplierName = "Origin";
		tariff11 = 25.3781;
		tariff11SupplyCharge = 28.7870;
		tariff33 = 17.1545;
		//feed in tariff = 8c/kWh for <5kW inverters
		//feedInTariff = 6c/kWh for <100kW inverters
		feedInTariff = 8;	
	}
	
	public Tariffs(String supplierName){
		//TODO get data from datastore
	}
	
	public double getNormalTariff(){
		return normalTariff;
	}
	
	public double getOffPeakTariff(){
		return offPeakTariff;
	}
	
	public double getFeedInTariff(){
		return feedInTariff;
	}
	
	public int getAnnualTariffIncrease(){
		return annualTariffIncrease;
	}
}
