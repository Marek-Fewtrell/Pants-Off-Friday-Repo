package project.server;

import java.util.ArrayList;

import project.client.SolarPowerService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SolarPowerServiceImpl extends RemoteServiceServlet implements SolarPowerService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String SolarPowerServer(ArrayList<String> calcumalations) throws IllegalArgumentException {
		
		//Calculations calcs = new Calculations(solarPanel, numPanels, suburb, inverter, energyProvider, daytimeUsage, efficiencyLoss);
		
		
		
		
		
		
		return "Well Hello: ";
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	/*private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}*/
	
}
