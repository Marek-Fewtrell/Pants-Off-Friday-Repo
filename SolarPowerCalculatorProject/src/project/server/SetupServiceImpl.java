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
		ArrayList<String>panels = new ArrayList<String>();
		panels = db.getAllSolarPanelSerials();
		for(int i=0; i<panels.size(); i++){
			//out.print("<option>" + panels.get(i) +"</option>");
			array1.add(panels.get(i));
		}
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
			String allBrands = inverterBrand.get(i1).toString();
			/*if (i1 < inverterBrand.size()-1) {
				allBrands.concat(",");
			}*/
			inverterSerial = db.getInverterSerialNumberByBrand(inverterBrand.get(i1).toString());
			//allBrands = allBrands + "," + inverterSerial.get(i1);
			//Loop through all models of the brands of inverters
			for(int i2 = 0; i2<inverterSerial.size(); i2++) {
				allBrands = allBrands + "," + inverterSerial.get(i2).toString();
				
			}
			array2.add(allBrands);
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
			//out.print("<option>" + energyCompanies.get(i) + "</option>");
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
