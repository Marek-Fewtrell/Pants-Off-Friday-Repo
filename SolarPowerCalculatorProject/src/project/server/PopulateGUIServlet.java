package project.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PopulateGUIServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {
		
		Database db = new Database();
		
		String respondInvBrand = "INVERTERBRAND";
		ArrayList<String> inverterBrand = db.getAllInverterBrands();
		for (int i=0 ; i<inverterBrand.size(); i++) {
				respondInvBrand += "," + inverterBrand.get(i);
				ArrayList<String> invBrandModel = db.getInverterSerialNumberByBrand(inverterBrand.get(i));
				for (int count=0 ; count<invBrandModel.size() ; count++) {
					respondInvBrand += "," + invBrandModel.get(count);
				}
				respondInvBrand += "*";
		}
		
		String respondPanBrand = "PANELBRAND";
		ArrayList<String> panelBrand = db.getAllSolarPanelBrands();
		for (int i=0 ; i<panelBrand.size(); i++) {
			respondPanBrand += "," + panelBrand.get(i);
			ArrayList<String> panelBrandModel = db.getSolarPanelSerialNumberByBrand(panelBrand.get(i));
			for (int count=0 ; count<panelBrandModel.size() ; count++) {
				respondPanBrand += "," + panelBrandModel.get(count);
			}
			respondPanBrand += "*";
		}
		
		String respondEnergy = "ENERGY";
		ArrayList<String> energy = db.getAllEnergyProviders();
		for (int i=0 ; i<energy.size(); i++) {
			respondEnergy += "," + energy.get(i);
		}
		
		response.getWriter().println(respondInvBrand);
		response.getWriter().println(respondPanBrand);
		response.getWriter().println(respondEnergy);
		response.getWriter().println("Done");
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        
    }

}
