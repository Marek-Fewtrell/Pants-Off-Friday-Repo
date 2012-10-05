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
		
		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<String> array2 = new ArrayList<String>();
		ArrayList<String> array3 = new ArrayList<String>();
		ArrayList<ArrayList<String>> arrayOut = new ArrayList<ArrayList<String>>();
		
		ArrayList<String>panels = new ArrayList<String>();
		panels = db.getAllSolarPanelSerials();
		for(int i=0; i<panels.size(); i++){
			//out.print("<option>" + panels.get(i) +"</option>");
			array1.add(panels.get(i));
		}
		
		
		ArrayList<String>inverters = new ArrayList<String>();
		inverters = db.getAllInverterSerials();
		for(int i=0; i<inverters.size(); i++){
			//out.print("<option>" + inverters.get(i) +"</option>");
			array2.add(inverters.get(i));
		}
		
		
		ArrayList <String> energyCompanies = new ArrayList<String>();
		energyCompanies = db.getAllEnergyProviders();
		for(int i=0; i<energyCompanies.size(); i++){
			//out.print("<option>" + energyCompanies.get(i) + "</option>");
			array3.add(energyCompanies.get(i));
		}
		
		arrayOut.add(array1);
		arrayOut.add(array2);
		arrayOut.add(array3);
		
		return arrayOut;
	}
	
	
	
	
	
	
}
