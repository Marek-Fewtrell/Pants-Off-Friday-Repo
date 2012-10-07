package project.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import project.client.SetupService;

public class SetupServiceImpl extends RemoteServiceServlet implements SetupService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Database db = new Database();
	
	@Override
	public ArrayList<ArrayList<String>> SetupServer() throws IllegalArgumentException {
		
		ArrayList<String> array1 = new ArrayList<String>();//Holds Panel info
		ArrayList<String> array2 = new ArrayList<String>();//Holds Inverter info
		ArrayList<String> array3 = new ArrayList<String>();//Holds Energy Company info
		//This is the array which is returned
		ArrayList<ArrayList<String>> arrayOut = new ArrayList<ArrayList<String>>();
		
		/* 
		 * Start Panel info collection
		 */
		ArrayList<String>panelBrand = new ArrayList<String>();
		ArrayList<String>panelSerial = new ArrayList<String>();
		panelBrand = db.getAllSolarPanelBrands();
		//Loop through all the brands of panels
		for(int i1=0; i1<panelBrand.size(); i1++){
			String allSolarBrands = panelBrand.get(i1).toString();
			panelSerial = db.getSolarPanelSerialNumberByBrand(panelBrand.get(i1).toString());
			//Loop through all models of the brands of inverters
			for(int i2 = 0; i2<panelSerial.size(); i2++) {
				allSolarBrands = allSolarBrands + "," + panelSerial.get(i2).toString();
				
			}
			array1.add(allSolarBrands);
		}
		
		/*//Old way of getting the data.
		ArrayList<String>panels = new ArrayList<String>();
		panels = db.getAllSolarPanelSerials();
		for(int i=0; i<panels.size(); i++){
			//out.print("<option>" + panels.get(i) +"</option>");
			array1.add(panels.get(i));
		}*/
		/* 
		 * Stop Panel info collection
		 */
		
		/* 
		 * Start Inverter info collection
		 */
		ArrayList<String>inverterBrand = new ArrayList<String>();
		ArrayList<String>inverterSerial = new ArrayList<String>();
		inverterBrand = db.getAllInverterBrands();
		//Loop through all the brands of inverters
		for(int i1=0; i1<inverterBrand.size(); i1++){
			String allInverterBrands = inverterBrand.get(i1).toString();
			inverterSerial = db.getInverterSerialNumberByBrand(inverterBrand.get(i1).toString());
			//Loop through all models of the brands of inverters
			for(int i2 = 0; i2<inverterSerial.size(); i2++) {
				allInverterBrands = allInverterBrands + "," + inverterSerial.get(i2).toString();
			}
			array2.add(allInverterBrands);
		}
		/*
		 * Stop Inverter info collection
		 */
		
		/*
		 * Start Energy Provider info collection
		 */
		ArrayList <String> energyCompanies = new ArrayList<String>();
		energyCompanies = db.getAllEnergyProviders();
		for(int i=0; i<energyCompanies.size(); i++){
			array3.add(energyCompanies.get(i));
		}
		/*
		 * Stop Energy Provider info collection
		 */
		
		//Add collected data to item being sent
		arrayOut.add(array1);
		arrayOut.add(array2);
		arrayOut.add(array3);
		
		//return the item
		return arrayOut;
	}
	
	
	
	
	
	
}
